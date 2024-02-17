package com.example.githubusernavigationdanapi.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.githubusernavigationdanapi.model.response.DetailUserResponse
import com.example.githubusernavigationdanapi.model.response.ListFollowerResponseItem
import com.example.githubusernavigationdanapi.model.response.ListFollowingResponseItem
import com.example.githubusernavigationdanapi.model.retrofit.ApiConfig
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class DetailViewModel:ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _detailUser = MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailUser

    private var _listFollower = MutableLiveData<ArrayList<ListFollowerResponseItem>>()
    var listFollower:LiveData<ArrayList<ListFollowerResponseItem>> = _listFollower

    private var _listFollowing = MutableLiveData<ArrayList<ListFollowingResponseItem>>()
    var listFollowing:LiveData<ArrayList<ListFollowingResponseItem>> = _listFollowing

    companion object{
        private const val TAG = "DetailViewModel"
    }

    fun detailUser(username:String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().detailUser(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailUser.value = response.body() as DetailUserResponse
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun listFollowers(username:String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().followersUser(username)
        client.enqueue(object : Callback<List<ListFollowerResponseItem>> {
            override fun onResponse(
                call: Call<List<ListFollowerResponseItem>>,
                response: Response<List<ListFollowerResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollower.value = response.body() as ArrayList<ListFollowerResponseItem>
                } else {
                    Log.e(TAG, "gagal: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ListFollowerResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "gagal: $username")
                Log.e(TAG, "gagal request: ${t.message.toString()}")
            }
        })
    }

    fun listFollowings(username:String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().followingUser(username)
        client.enqueue(object : Callback<List<ListFollowingResponseItem>> {
            override fun onResponse(
                call: Call<List<ListFollowingResponseItem>>,
                response: Response<List<ListFollowingResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    Log.e(TAG, "data masuk nih: ${response.body()}")
                    _listFollowing.value = response.body() as ArrayList<ListFollowingResponseItem>
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ListFollowingResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
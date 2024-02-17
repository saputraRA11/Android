package com.example.githubusernavigationdanapi.viewModel
import android.util.Log
import androidx.lifecycle.*
import com.example.githubusernavigationdanapi.model.response.ItemsItem
import com.example.githubusernavigationdanapi.model.response.SearchUserResponse
import com.example.githubusernavigationdanapi.model.retrofit.ApiConfig
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class MainViewModel: ViewModel() {
    private var _data = MutableLiveData<SearchUserResponse>()
     var data:LiveData<SearchUserResponse> = _data

    private var _items = MutableLiveData<ArrayList<ItemsItem>>()
    var items:LiveData<ArrayList<ItemsItem>> = _items

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainViewModel"
    }

    fun searchUser(username:String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchUser(username)
        client.enqueue(object : Callback<SearchUserResponse> {
            override fun onResponse(
                call: Call<SearchUserResponse>,
                response: Response<SearchUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _data.value = response.body()
                    _items.value = response.body()?.items as ArrayList<ItemsItem>
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
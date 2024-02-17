package com.example.githubusernavigationdanapi.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusernavigationdanapi.databinding.FragmentFollowingBinding
import com.example.githubusernavigationdanapi.model.response.ListFollowingResponseItem
import com.example.githubusernavigationdanapi.view.ListDataFollowingAdapter
import com.example.githubusernavigationdanapi.view.followingFilterList
import com.example.githubusernavigationdanapi.viewModel.DetailViewModel

class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListDataFollowingAdapter
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).
        get(DetailViewModel::class.java)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.detailUser.observe(requireActivity()) {
            viewModel.listFollowings(it.login!!)
        }

        showRecyclerList()
        viewModel.listFollowing.observe(requireActivity()) {
            listData ->
            adapter = ListDataFollowingAdapter(listData)
            binding.recycleViewFollowing.adapter = adapter
        }

        viewModel.isLoading.observe(requireActivity()){
            showLoading(it)
        }
    }

    private fun showRecyclerList() {
        binding.recycleViewFollowing.layoutManager = LinearLayoutManager(activity)
        val listDataAdapter =
            ListDataFollowingAdapter(followingFilterList)

        listDataAdapter.setOnItemClickCallback(object :
            ListDataFollowingAdapter.OnItemClickCallback {
            override fun onItemClicked(DataFollowing: ListFollowingResponseItem) {
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarFollowing.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}

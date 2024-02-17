package com.example.githubusernavigationdanapi.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusernavigationdanapi.databinding.FragmentFollowersBinding
import com.example.githubusernavigationdanapi.model.response.ListFollowerResponseItem
import com.example.githubusernavigationdanapi.view.ListDataFollowersAdapter
import com.example.githubusernavigationdanapi.view.followersFilterList
import com.example.githubusernavigationdanapi.viewModel.DetailViewModel

class FollowersFragment : Fragment() {
    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListDataFollowersAdapter
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listFollower.observe(requireActivity()){
            listData ->
            adapter = ListDataFollowersAdapter(listData)
            showRecyclerList()
        }

        viewModel.isLoading.observe(requireActivity()){
            showLoading(it)
        }
    }

    private fun showRecyclerList() {
        binding.recycleViewFollowers.layoutManager = LinearLayoutManager(activity)
        val listDataAdapter =
            ListDataFollowersAdapter(followersFilterList)
        binding.recycleViewFollowers.adapter = adapter

        listDataAdapter.setOnItemClickCallback(object :
            ListDataFollowersAdapter.OnItemClickCallback {
            override fun onItemClicked(DataFollowers: ListFollowerResponseItem) {
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarFollowers.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}

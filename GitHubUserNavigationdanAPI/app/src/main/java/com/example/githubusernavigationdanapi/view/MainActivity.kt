package com.example.githubusernavigationdanapi.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusernavigationdanapi.databinding.ActivityMainBinding
import com.example.githubusernavigationdanapi.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var title: String = "Github User's"
    private lateinit var adapter: ListDataUsersAdapter
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBarTitle(title)
        mainViewModel.searchUser("a")
        recyclerViewConfig()
        mainViewModel.items.observe(this){
                it->
            adapter = ListDataUsersAdapter(it)
            binding.recycleView.adapter = adapter
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }


        searchData()
    }

    private fun searchData() {
        binding.userSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) {
                    return true
                } else {
                    mainViewModel.searchUser(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun recyclerViewConfig() {
        binding.recycleView.layoutManager = LinearLayoutManager(binding.recycleView.context)
        binding.recycleView.setHasFixedSize(true)
        binding.recycleView.addItemDecoration(
            DividerItemDecoration(
                binding.recycleView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar!!.title = title
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
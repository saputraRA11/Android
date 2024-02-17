package com.example.githubusernavigationdanapi.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubusernavigationdanapi.databinding.ActivityDetailBinding
import com.example.githubusernavigationdanapi.viewModel.DetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.example.githubusernavigationdanapi.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following,
        )
    }

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPagerConfig()
        setData()
        detailViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun viewPagerConfig() {
        val viewPagerDetailAdapter = ViewPageDetailAdapter(this)
        binding.viewPager.adapter = viewPagerDetailAdapter
        TabLayoutMediator(binding.tabs,binding.viewPager) {
            tab,position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar!!.title = title
        }
    }

    private fun setData() {
        val userName = intent.getStringExtra(EXTRA_DATA)
        detailViewModel.detailUser(userName!!)
        detailViewModel.listFollowers(userName!!)
        detailViewModel.detailUser.observe(this) {
            dataUser  ->
            setActionBarTitle(dataUser?.name.toString())
            binding.name.text = dataUser?.name.toString()
            binding.username.text = "( ${dataUser?.login.toString()}  )"
            binding.company.text = dataUser?.company.toString()
            binding.location.text = dataUser?.location.toString()
            binding.followerss.text = dataUser?.followers.toString()
            binding.followings.text = dataUser?.following.toString()
            Glide.with(this)
                .load(dataUser?.avatarUrl.toString())
                .into(binding.avatars)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarDetail.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}

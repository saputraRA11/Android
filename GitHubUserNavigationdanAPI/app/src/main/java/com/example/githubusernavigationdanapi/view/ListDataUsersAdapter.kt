package com.example.githubusernavigationdanapi.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubusernavigationdanapi.databinding.ItemRowUsersBinding
import com.example.githubusernavigationdanapi.model.response.ItemsItem
import kotlin.collections.ArrayList

var userFilterList = ArrayList<ItemsItem>()

class ListDataUsersAdapter(private var listData: ArrayList<ItemsItem>) :
    RecyclerView.Adapter<ListDataUsersAdapter.ListDataHolder>() {

    init {
        userFilterList = listData
    }

    class ListDataHolder(var binding: ItemRowUsersBinding) : RecyclerView.ViewHolder(binding.root)


    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var mcontext:Context

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(dataUsers: ItemsItem)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListDataHolder {
        val binding = ItemRowUsersBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        mcontext = binding.root.context
        return ListDataHolder(binding)
    }

    override fun getItemCount(): Int {
        return userFilterList.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        val data = userFilterList[position]
        Glide.with(holder.itemView.context)
            .load(data.avatarUrl)
            .apply(RequestOptions().override(250, 250))
            .into(holder.binding.avatar)
        holder.binding.name.text = data.login

        holder.binding.avatar.setOnClickListener {
            val intentDetail = Intent(mcontext, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_DATA, data.login)
            mcontext.startActivity(intentDetail)
        }
    }
}
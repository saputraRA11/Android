package com.example.githubusernavigationdanapi.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubusernavigationdanapi.databinding.ItemRowUsersBinding
import com.example.githubusernavigationdanapi.model.response.ListFollowerResponseItem

var followersFilterList = ArrayList<ListFollowerResponseItem>()

class ListDataFollowersAdapter(listData: ArrayList<ListFollowerResponseItem>) :
    RecyclerView.Adapter<ListDataFollowersAdapter.ListDataHolder>() {

    init {
        followersFilterList = listData
    }

    class ListDataHolder(var binding: ItemRowUsersBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(DataFollowers: ListFollowerResponseItem)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListDataHolder {
        val binding = ItemRowUsersBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListDataHolder(binding)
    }

    override fun getItemCount(): Int = followersFilterList.size

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        val data = followersFilterList[position]
        Glide.with(holder.itemView.context)
            .load(data.avatarUrl)
            .apply(RequestOptions().override(250, 250))
            .into(holder.binding.avatar)
        holder.binding.name.text = data.login
    }
}
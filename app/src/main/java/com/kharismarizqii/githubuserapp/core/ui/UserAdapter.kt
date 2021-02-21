package com.kharismarizqii.githubuserapp.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kharismarizqii.githubuserapp.R
import com.kharismarizqii.githubuserapp.core.domain.model.User
import com.kharismarizqii.githubuserapp.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var listData = ArrayList<User>()
    var onItemClick: ((User) -> Unit)? = null

    fun setData(newListData: List<User>?){
        if (newListData == null) return
        Log.e("User Adapter", "newListData: $newListData")
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemUserBinding.bind(itemView)
        fun bind(data: User){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.avatarUrl)
                    .into(ivUser)
                tvName.text = data.username
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}
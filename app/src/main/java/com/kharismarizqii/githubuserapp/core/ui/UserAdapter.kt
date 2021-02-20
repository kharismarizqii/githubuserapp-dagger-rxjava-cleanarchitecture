package com.kharismarizqii.githubuserapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.githubuserapp.R
import com.kharismarizqii.githubuserapp.core.domain.model.User
import com.kharismarizqii.githubuserapp.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var listData = ArrayList<User>()
    var onItemClick: ((User) -> Unit)? = null

    fun setData(newListData: List<User>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemUserBinding.bind(itemView)
        fun bind(data: User){
            with(binding){
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
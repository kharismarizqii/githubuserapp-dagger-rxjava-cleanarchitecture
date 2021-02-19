package com.kharismarizqii.githubuserapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("items")
    val list : List<UserResponse>
)

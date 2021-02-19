package com.kharismarizqii.githubuserapp.core.data.source.remote.network

import com.kharismarizqii.githubuserapp.core.data.source.remote.response.UserListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("search/users")
    fun getSearchUser(q: String): Call<UserListResponse>
}
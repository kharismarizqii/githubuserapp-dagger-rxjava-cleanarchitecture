package com.kharismarizqii.githubuserapp.core.data.source.remote.network

import com.kharismarizqii.githubuserapp.core.data.source.remote.response.UserListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token aa22cb9bfab3d439f33e98d011b7cefd7b88ca05")
    fun getSearchUser(
        @Query("q") q: String
    ): Call<UserListResponse>
}
package com.kharismarizqii.githubuserapp.core.data.source.remote.network

import com.kharismarizqii.githubuserapp.core.data.source.remote.response.UserListResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token bb10daf459ea7caa87b28e88044e231c1c0f18fd")
    fun getSearchUser(
        @Query("q") q: String
    ): Flowable<UserListResponse>
}
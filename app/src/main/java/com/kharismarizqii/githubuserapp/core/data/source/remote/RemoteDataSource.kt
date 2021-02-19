package com.kharismarizqii.githubuserapp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kharismarizqii.githubuserapp.core.data.source.remote.network.ApiResponse
import com.kharismarizqii.githubuserapp.core.data.source.remote.network.ApiService
import com.kharismarizqii.githubuserapp.core.data.source.remote.response.UserListResponse
import com.kharismarizqii.githubuserapp.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService){
    fun getSearchUser(q: String): LiveData<ApiResponse<List<UserResponse>>>{
        val resultData = MutableLiveData<ApiResponse<List<UserResponse>>>()

        val client = apiService.getSearchUser(q)

        client.enqueue(object : Callback<UserListResponse>{
            override fun onResponse(
                call: Call<UserListResponse>,
                response: Response<UserListResponse>
            ) {
                val dataArray = response.body()?.list
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })
        return resultData
    }
}
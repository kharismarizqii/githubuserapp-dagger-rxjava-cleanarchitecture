package com.kharismarizqii.githubuserapp.core.data.source.remote

import android.util.Log
import com.kharismarizqii.githubuserapp.core.data.source.remote.network.ApiResponse
import com.kharismarizqii.githubuserapp.core.data.source.remote.network.ApiService
import com.kharismarizqii.githubuserapp.core.data.source.remote.response.UserResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getSearchUser(q: String): Flowable<ApiResponse<List<UserResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<UserResponse>>>()

        val client = apiService.getSearchUser(q)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.list
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}
package com.kharismarizqii.githubuserapp.core.domain.repository

import com.kharismarizqii.githubuserapp.core.data.Resource
import com.kharismarizqii.githubuserapp.core.domain.model.User
import io.reactivex.Flowable

interface IUserRepository {
    fun getSearchUser(q: String): Flowable<Resource<List<User>>>
}
package com.kharismarizqii.githubuserapp.core.domain.usecase

import com.kharismarizqii.githubuserapp.core.data.Resource
import com.kharismarizqii.githubuserapp.core.domain.model.User
import io.reactivex.Flowable

interface UserUseCase {
    fun getSearchUser(q: String): Flowable<Resource<List<User>>>
}
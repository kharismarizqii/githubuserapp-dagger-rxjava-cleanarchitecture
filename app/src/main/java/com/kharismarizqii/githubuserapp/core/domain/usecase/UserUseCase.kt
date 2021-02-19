package com.kharismarizqii.githubuserapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.kharismarizqii.githubuserapp.core.data.Resource
import com.kharismarizqii.githubuserapp.core.domain.model.User

interface UserUseCase {
    fun getSearchUser(q: String): LiveData<Resource<List<User>>>
}
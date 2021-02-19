package com.kharismarizqii.githubuserapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.kharismarizqii.githubuserapp.core.data.Resource
import com.kharismarizqii.githubuserapp.core.domain.model.User

interface IUserRepository {
    fun getSearchUser(q: String): LiveData<Resource<List<User>>>
}
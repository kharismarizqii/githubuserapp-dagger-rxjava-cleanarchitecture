package com.kharismarizqii.githubuserapp.core.data.source.local

import androidx.lifecycle.LiveData
import com.kharismarizqii.githubuserapp.core.data.source.local.entity.UserEntity
import com.kharismarizqii.githubuserapp.core.data.source.local.room.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val userDao: UserDao) {
    fun getSearchUser(q: String) : LiveData<List<UserEntity>> = userDao.getSearchUser(q)
    fun insertSearchUser(userList : List<UserEntity>) = userDao.insertSearchUser(userList)
}
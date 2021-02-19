package com.kharismarizqii.githubuserapp.core.data.source.local

import androidx.lifecycle.LiveData
import com.kharismarizqii.githubuserapp.core.data.source.local.entity.UserEntity
import com.kharismarizqii.githubuserapp.core.data.source.local.room.UserDao

class LocalDataSource private constructor(private val userDao: UserDao) {
    fun getSearchUser(q: String) : LiveData<List<UserEntity>> = userDao.getSearchUser(q)
    fun insertSearchUser(userList : List<UserEntity>) = userDao.insertSearchUser(userList)
}
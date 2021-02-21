package com.kharismarizqii.githubuserapp.core.data.source.local

import com.kharismarizqii.githubuserapp.core.data.source.local.entity.UserEntity
import com.kharismarizqii.githubuserapp.core.data.source.local.room.UserDao
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val userDao: UserDao) {
    fun getSearchUser(q: String): Flowable<List<UserEntity>> = userDao.getSearchUser(q)
    fun insertSearchUser(userList: List<UserEntity>) = userDao.insertSearchUser(userList)
}
package com.kharismarizqii.githubuserapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kharismarizqii.githubuserapp.core.data.source.local.entity.DetailUserEntity
import com.kharismarizqii.githubuserapp.core.data.source.local.entity.UserEntity

@Database(entities = [UserEntity::class, DetailUserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
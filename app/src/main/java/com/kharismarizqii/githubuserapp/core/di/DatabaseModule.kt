package com.kharismarizqii.githubuserapp.core.di

import android.content.Context
import androidx.room.Room
import com.kharismarizqii.githubuserapp.core.data.source.local.room.UserDao
import com.kharismarizqii.githubuserapp.core.data.source.local.room.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabases(context: Context): UserDatabase = Room.databaseBuilder(
        context,
        UserDatabase::class.java, "User.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideUserDao(database: UserDatabase) : UserDao = database.userDao()
}
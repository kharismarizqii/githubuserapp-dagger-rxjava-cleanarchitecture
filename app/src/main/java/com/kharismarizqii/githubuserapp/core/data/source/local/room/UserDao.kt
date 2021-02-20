package com.kharismarizqii.githubuserapp.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kharismarizqii.githubuserapp.core.data.source.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user where username LIKE :q")
    fun getSearchUser(q: String): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchUser(user : List<UserEntity>)
}
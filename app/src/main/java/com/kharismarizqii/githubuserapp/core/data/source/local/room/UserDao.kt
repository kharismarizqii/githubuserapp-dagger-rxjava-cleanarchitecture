package com.kharismarizqii.githubuserapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kharismarizqii.githubuserapp.core.data.source.local.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Query("SELECT * FROM user where username LIKE :q")
    fun getSearchUser(q: String): Flowable<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchUser(user: List<UserEntity>): Completable
}
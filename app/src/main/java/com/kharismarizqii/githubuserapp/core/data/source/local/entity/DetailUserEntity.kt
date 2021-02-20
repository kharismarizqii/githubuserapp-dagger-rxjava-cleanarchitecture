package com.kharismarizqii.githubuserapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_user")
data class DetailUserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "followers")
    var followers : Int,

    @ColumnInfo(name = "following")
    var following : Int
)
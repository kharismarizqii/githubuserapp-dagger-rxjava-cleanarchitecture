package com.kharismarizqii.githubuserapp.core.utils

import com.kharismarizqii.githubuserapp.core.data.source.local.entity.UserEntity
import com.kharismarizqii.githubuserapp.core.data.source.remote.response.UserResponse
import com.kharismarizqii.githubuserapp.core.domain.model.User

object DataMapper {
    fun mapUserResponsesToEntities(input: List<UserResponse>) : List<UserEntity>{
        val userList = ArrayList<UserEntity>()
        input.map{
            val user = UserEntity(
                id = it.id,
                username = it.username,
                avatarUrl = it.avatarUrl
            )
            userList.add(user)
        }
        return userList
    }

    fun mapUserEntitiesToDomain(input: List<UserEntity>) : List<User>{
        val userList = ArrayList<User>()
        input.map {
            val user = User(
                id = it.id,
                username = it.username,
                avatarUrl = it.avatarUrl
            )
            userList.add(user)
        }
        return userList
    }
}
package com.kharismarizqii.githubuserapp.core.domain.model

data class DetailUser(
    val id: String,
    val username: String,
    val avatarUrl: String,
    val name: String,
    val followers : Int,
    val following: Int
)

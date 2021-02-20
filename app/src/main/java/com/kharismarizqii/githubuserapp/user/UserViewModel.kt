package com.kharismarizqii.githubuserapp.user

import androidx.lifecycle.ViewModel
import com.kharismarizqii.githubuserapp.core.domain.usecase.UserUseCase

class UserViewModel(private val userUseCase: UserUseCase): ViewModel() {
    fun getSearchUser(q: String) = userUseCase.getSearchUser(q)
}
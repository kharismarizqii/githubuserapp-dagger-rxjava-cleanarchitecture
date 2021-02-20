package com.kharismarizqii.githubuserapp.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kharismarizqii.githubuserapp.core.domain.usecase.UserUseCase
import com.kharismarizqii.githubuserapp.di.AppScope
import com.kharismarizqii.githubuserapp.user.UserViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(private val userUseCase: UserUseCase) :
    ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                UserViewModel(userUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}
package com.kharismarizqii.githubuserapp.di

import com.kharismarizqii.githubuserapp.core.domain.usecase.UserInteractor
import com.kharismarizqii.githubuserapp.core.domain.usecase.UserUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase
}
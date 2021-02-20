package com.kharismarizqii.githubuserapp.core.di

import com.kharismarizqii.githubuserapp.core.data.UserRepository
import com.kharismarizqii.githubuserapp.core.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(userRepository: UserRepository): IUserRepository
}
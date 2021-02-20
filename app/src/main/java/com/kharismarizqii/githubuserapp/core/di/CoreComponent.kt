package com.kharismarizqii.githubuserapp.core.di

import android.content.Context
import com.kharismarizqii.githubuserapp.core.domain.repository.IUserRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : CoreComponent
    }

    fun provideRepository() : IUserRepository
}
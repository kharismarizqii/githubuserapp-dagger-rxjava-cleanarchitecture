package com.kharismarizqii.githubuserapp.di

import com.kharismarizqii.githubuserapp.user.MainActivity
import com.kharismarizqii.githubuserapp.core.di.CoreComponent
import dagger.Component


@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
}
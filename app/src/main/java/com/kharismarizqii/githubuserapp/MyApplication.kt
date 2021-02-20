package com.kharismarizqii.githubuserapp

import android.app.Application
import com.kharismarizqii.githubuserapp.core.di.CoreComponent
import com.kharismarizqii.githubuserapp.core.di.DaggerCoreComponent
import com.kharismarizqii.githubuserapp.di.AppComponent
import com.kharismarizqii.githubuserapp.di.DaggerAppComponent

open class MyApplication: Application() {
    private val coreComponent : CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent : AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}
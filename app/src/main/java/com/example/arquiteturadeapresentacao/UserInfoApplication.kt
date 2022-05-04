package com.example.arquiteturadeapresentacao

import android.app.Application
import com.example.arquiteturadeapresentacao.di.UserInfoModule.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UserInfoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UserInfoApplication)
            modules(presentationModule)
        }
    }
}
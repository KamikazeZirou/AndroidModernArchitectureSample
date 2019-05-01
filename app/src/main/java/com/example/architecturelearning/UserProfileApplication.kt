package com.example.architecturelearning

import android.app.Application
import com.example.architecturelearning.com.example.architecturelearning.di.AppComponent
import com.example.architecturelearning.com.example.architecturelearning.di.AppModule
import com.example.architecturelearning.com.example.architecturelearning.di.DaggerAppComponent

class UserProfileApplication : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule()).build()
    }
}
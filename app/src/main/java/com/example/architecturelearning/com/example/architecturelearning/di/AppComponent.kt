package com.example.architecturelearning.com.example.architecturelearning.di

import com.example.architecturelearning.UserProfileApplication
import com.example.architecturelearning.UserProfileFragment
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, UserProfileFragmentModule::class])
interface AppComponent {
    fun inject(app: UserProfileApplication)
}
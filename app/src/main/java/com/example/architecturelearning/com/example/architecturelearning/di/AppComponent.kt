package com.example.architecturelearning.com.example.architecturelearning.di

import com.example.architecturelearning.UserProfileFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(fragment: UserProfileFragment)
}
package com.example.architecturelearning.com.example.architecturelearning.di

import android.app.Application
import com.example.architecturelearning.UserProfileApplication
import com.example.architecturelearning.UserProfileFragment
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import dagger.BindsInstance



@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, UserProfileFragmentModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: UserProfileApplication)
}
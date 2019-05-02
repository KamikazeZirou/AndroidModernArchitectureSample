package com.example.architecturelearning

import android.app.Application
import com.example.architecturelearning.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class UserProfileApplication : Application(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun onCreate() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        super.onCreate()
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> {
        return dispatchingFragmentInjector
    }
}
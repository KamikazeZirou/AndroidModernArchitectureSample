package com.example.architecturelearning

import android.app.Application
import android.support.v4.app.Fragment
import com.example.architecturelearning.com.example.architecturelearning.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class UserProfileApplication : Application(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        DaggerAppComponent.create().inject(this)
        super.onCreate()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingFragmentInjector
    }
}
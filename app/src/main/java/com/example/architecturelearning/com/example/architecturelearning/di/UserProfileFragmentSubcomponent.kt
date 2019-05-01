package com.example.architecturelearning.com.example.architecturelearning.di

import com.example.architecturelearning.UserProfileFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface UserProfileFragmentSubcomponent: AndroidInjector<UserProfileFragment> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<UserProfileFragment> {}
}
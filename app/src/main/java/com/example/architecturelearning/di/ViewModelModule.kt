package com.example.architecturelearning.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import androidx.lifecycle.ViewModel
import com.example.architecturelearning.UserProfileViewModel
import com.example.architecturelearning.ViewModelFactory
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    internal abstract fun bindUserProfileViewModel(repoViewModel: UserProfileViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
package com.example.architecturelearning

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import android.arch.lifecycle.ViewModel
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
package com.example.architecturelearning.di

import com.example.architecturelearning.UserProfileFragment
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.Module


@Module(
    subcomponents = [UserProfileFragmentSubcomponent::class],
    includes = [ViewModelModule::class]
)
abstract class UserProfileFragmentModule {
    @Binds
    @IntoMap
    @ClassKey(UserProfileFragment::class)
    internal abstract fun bindUserProfileFragmentInjectorFactory(factory: UserProfileFragmentSubcomponent.Factory): AndroidInjector.Factory<*   >
}
package com.example.architecturelearning

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {
    private companion object {
        const val GITHUB_BASE_URL = "https://api.github.com/"
    }

    @Provides
    @Singleton
    fun provideUserRepository(service: GitHubService): UserRepository = UserRepository(service)

    @Provides
    @Singleton
    fun provideGitHubService(): GitHubService =
        Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubService::class.java)
}
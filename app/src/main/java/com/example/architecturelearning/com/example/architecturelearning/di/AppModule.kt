package com.example.architecturelearning.com.example.architecturelearning.di

import com.example.architecturelearning.GitHubService
import com.example.architecturelearning.UserRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {
    private companion object {
        const val GITHUB_BASE_URL = "https://api.github.com/"
    }

    @Provides
    @Singleton
    fun provideUserRepository(service: GitHubService): UserRepository =
        UserRepository(service)

    @Provides
    @Singleton
    fun provideGitHubService(): GitHubService =
        Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubService::class.java)
}
package com.example.architecturelearning

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    private companion object {
        const val GITHUB_BASE_URL = "https://api.github.com/"
    }

    @Provides
    @Singleton
    fun provideGitHubService(): GitHubService =
        Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubService::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(service: GitHubService): UserRepository = UserRepository(service)
}
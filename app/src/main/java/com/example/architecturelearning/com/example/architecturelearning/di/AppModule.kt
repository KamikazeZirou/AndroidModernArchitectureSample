package com.example.architecturelearning.com.example.architecturelearning.di

import com.example.architecturelearning.GitHubService
import com.example.architecturelearning.UserRepository
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

    // このProvide関数は不要。
    // UserRepositoryのコンストラクタはGitHubServiceのみに依存しているため。
    // GitHubServiceのProvide関数があると、この関数がなくてもInjectしてくれる。
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
package com.example.architecturelearning.com.example.architecturelearning.di

import com.example.architecturelearning.GitHubService
import com.example.architecturelearning.UserRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
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
    fun provideGitHubService(): GitHubService {
        // GitHubのAPIのResponseはsnakecase。
        // それをKotlin上のcamelCaseのフィールドに変換できるようにする指定。
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

        return Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GitHubService::class.java)
    }

}
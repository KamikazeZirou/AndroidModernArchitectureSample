package com.example.architecturelearning.di

import android.app.Application
import com.example.architecturelearning.GitHubService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.example.architecturelearning.UserDao
import androidx.room.Room
import com.example.architecturelearning.UserDatabase
import java.util.concurrent.Executor
import java.util.concurrent.Executors


@Module
class AppModule {
    private companion object {
        const val GITHUB_BASE_URL = "https://api.github.com/"
    }

    // このProvide関数は不要。
    // UserRepositoryのコンストラクタはGitHubServiceのみに依存しているため。
    // GitHubServiceのProvide関数があると、この関数がなくてもInjectしてくれる。
//    @Provides
//    @Singleton
//    fun provideUserRepository(service: GitHubService): UserRepository =
//        UserRepository(service)

    @Provides
    @Singleton
    fun provideDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(
            application,
            UserDatabase::class.java, "MyDatabase.db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

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
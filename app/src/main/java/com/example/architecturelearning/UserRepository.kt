package com.example.architecturelearning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository
    @Inject constructor(private val webservice: GitHubService,
                        private val userDao: UserDao,
                        private val executor: Executor) {
    fun getUser(loginName: String): LiveData<User> {
        refreshUser(loginName)
        return userDao.load(loginName)
    }

    private fun refreshUser(loginName: String) {
        executor.execute {
            val userExists = userDao.hasUser(loginName, System.currentTimeMillis() - 3600 * 1000)
            if (userExists) {
                return@execute
            }

            val response = webservice.getUser(loginName).execute()
            val user = response.body()!!
            user.lastRefresh = System.currentTimeMillis()
            userDao.save(user)
        }
    }
}
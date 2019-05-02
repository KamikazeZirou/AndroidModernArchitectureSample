package com.example.architecturelearning

import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository
    @Inject constructor(private val webservice: GitHubService) {

    private val cache: MutableMap<String, User> = mutableMapOf()

    fun getUser(username: String, result: MutableLiveData<User>) {
        cache[username]?.let {
            result.value = it
            return
        }

        cache[username] = User.EMPTY_USER

        webservice.getUser(username).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                result.value = user

                if (user != null) {
                    cache[username] = user
                } else {
                    cache.remove(username)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
package com.example.architecturelearning

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.graphics.Bitmap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository
    @Inject constructor(private val webservice: GitHubService) {

    private val cache: MutableMap<String, User> = mutableMapOf()

    fun getUser(userId: String, result: MutableLiveData<User>) {
        cache[userId]?.let {
            result.value = it
            return
        }

        cache[userId] = User.EMPTY_USER

        webservice.getUser(userId).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                result.value = user

                if (user != null) {
                    cache[userId] = user
                } else {
                    cache.remove(userId)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
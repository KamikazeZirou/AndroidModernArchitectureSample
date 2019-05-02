package com.example.architecturelearning

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository
    @Inject constructor(private val webservice: GitHubService) {

    private val cache: MutableMap<String, LiveData<User>> = mutableMapOf()

    fun getUser(userId: String): LiveData<User> {
        cache[userId]?.let {
            return it
        }

        val data = MutableLiveData<User>()
        cache[userId] = data

        webservice.getUser(userId).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return data
    }
}
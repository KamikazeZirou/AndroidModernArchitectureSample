package com.example.architecturelearning

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(val webservice: GitHubService) {
    fun getUser(userId: String): LiveData<User> {
        val data = MutableLiveData<User>()

        // TODO 毎回通信しないようにする
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
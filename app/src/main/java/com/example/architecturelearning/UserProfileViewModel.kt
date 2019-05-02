package com.example.architecturelearning

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import javax.inject.Inject


class UserProfileViewModel
    @Inject constructor(private val userRepo: UserRepository): ViewModel() {
    var user: MutableLiveData<User> = MutableLiveData()

    fun load(username: String) {
        if (user?.value?.name != username) {
            userRepo.getUser(username, user)
        }
    }
}
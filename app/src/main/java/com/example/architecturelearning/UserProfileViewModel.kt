package com.example.architecturelearning

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData


class UserProfileViewModel(): ViewModel() {
    lateinit var userRepo: UserRepository
    lateinit var user: LiveData<User>

    fun init(userRepo: UserRepository, userId: String) {
        this.userRepo = userRepo
        user = userRepo.getUser(userId)
    }
}
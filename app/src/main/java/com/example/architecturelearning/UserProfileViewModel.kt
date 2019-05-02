package com.example.architecturelearning

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.graphics.Bitmap
import javax.inject.Inject


class UserProfileViewModel
    @Inject constructor(private val userRepo: UserRepository): ViewModel() {
    var user: LiveData<User>? = null

    fun init(userId: String) {
        if (user == null) {
            user = userRepo.getUser(userId)
        }
    }
}
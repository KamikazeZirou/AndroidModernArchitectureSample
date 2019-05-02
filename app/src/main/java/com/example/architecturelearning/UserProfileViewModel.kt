package com.example.architecturelearning

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject


class UserProfileViewModel
    @Inject constructor(private val userRepo: UserRepository): ViewModel() {
    var user: LiveData<User>? = null

    fun init(loginName: String) {
        if (user == null || user?.value?.login != loginName) {
            user = userRepo.getUser(loginName)
        }
    }
}
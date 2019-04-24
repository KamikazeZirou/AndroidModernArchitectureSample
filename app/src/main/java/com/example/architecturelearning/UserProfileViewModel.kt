package com.example.architecturelearning

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData


class UserProfileViewModel: ViewModel() {
    var id: String = ""
    var user = MutableLiveData<User>()
}
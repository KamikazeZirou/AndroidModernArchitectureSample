package com.example.architecturelearning

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import java.net.URL
import java.net.URLConnection
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


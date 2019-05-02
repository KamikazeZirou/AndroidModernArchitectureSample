package com.example.architecturelearning

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.architecturelearning.databinding.UserProfileBinding
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import retrofit2.http.Url
import java.net.URL
import java.net.URLConnection
import javax.inject.Inject

class UserProfileFragment : Fragment() {
    companion object {
        private val TAG = UserProfileFragment::class.simpleName
        val UID_KEY = "uid"
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: UserProfileViewModel
    private lateinit var binding: UserProfileBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = UserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory).get(UserProfileViewModel::class.java)
        val userId = arguments?.getString(UID_KEY) ?: ""
        viewModel.init(userId)
        viewModel.user?.observe(this, object: Observer<User?> {
            override fun onChanged(t: User?) {
                t ?: return
                GetAvatarTask().execute(t.avatarUrl)
            }
        })

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    inner class GetAvatarTask: AsyncTask<String, Void, Bitmap>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): Bitmap? {
            var conn: URLConnection? = null
            try {
                val url = URL(params[0])
                conn = url.openConnection()
                conn.doInput = true
                conn.connect()
                return BitmapFactory.decodeStream(conn.getInputStream())
            } finally {
                conn?.getInputStream()?.close()
            }
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            binding.avatarView.setImageBitmap(result)
        }
    }
}
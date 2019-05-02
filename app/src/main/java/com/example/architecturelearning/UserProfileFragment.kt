package com.example.architecturelearning

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.architecturelearning.databinding.UserProfileBinding
import dagger.android.support.AndroidSupportInjection
import java.net.URL
import java.net.URLConnection
import javax.inject.Inject

class UserProfileFragment : androidx.fragment.app.Fragment() {
    companion object {
        private val TAG = UserProfileFragment::class.simpleName
        const val LOGINNAME_KEY = "loginName"
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
        val loginName = arguments?.getString(LOGINNAME_KEY) ?: ""
        initViewModel(loginName)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.upateButton.setOnClickListener {
            initViewModel(binding.editNameText.text.toString())
        }
    }

    private fun initViewModel(loginName: String) {
        viewModel.init(loginName)
        viewModel.user?.observe(this, object: Observer<User?> {
            override fun onChanged(t: User?) {
                t ?: return
                GetAvatarTask().execute(t.avatarUrl)
            }
        })
        binding.viewModel = viewModel
    }

    inner class GetAvatarTask: AsyncTask<String, Void, Bitmap>() {
        override fun onPreExecute() {
            super.onPreExecute()

            (binding.avatarView.drawable as? BitmapDrawable)?.bitmap?.let {
                binding.avatarView.setImageBitmap(null)
                it.recycle()
            }
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
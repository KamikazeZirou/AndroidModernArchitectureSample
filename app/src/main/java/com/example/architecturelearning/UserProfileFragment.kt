package com.example.architecturelearning

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.architecturelearning.databinding.UserProfileBinding
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
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

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
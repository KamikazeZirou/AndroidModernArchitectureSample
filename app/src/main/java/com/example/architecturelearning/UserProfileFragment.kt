package com.example.architecturelearning

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.architecturelearning.databinding.UserProfileBinding

class UserProfileFragment: Fragment() {
    companion object {
        val UID_KEY = "uid"
    }
    private lateinit var viewModel: UserProfileViewModel
    private lateinit var binding: UserProfileBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        viewModel.id = arguments?.getString(UID_KEY) ?: ""
        viewModel.user.value = User("zirou", "zirou.android@gmail.com")

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.upateButton.setOnClickListener {
            viewModel.user.value = User("tarou", "tarou.android@gmail.com")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = UserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
}
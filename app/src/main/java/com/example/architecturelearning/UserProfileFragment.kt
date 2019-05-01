package com.example.architecturelearning

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.architecturelearning.com.example.architecturelearning.di.AppModule
import com.example.architecturelearning.com.example.architecturelearning.di.DaggerAppComponent
import com.example.architecturelearning.databinding.UserProfileBinding
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
        super.onActivityCreated(savedInstanceState)

        // TODO もっと適切な場所でinject。Applicationクラス?
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(UserProfileViewModel::class.java)
        val userId = arguments?.getString(UID_KEY) ?: ""
        viewModel.init(userId)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
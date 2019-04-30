package com.example.architecturelearning

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.architecturelearning.databinding.UserProfileBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserProfileFragment: Fragment() {
    companion object {
        private val TAG = UserProfileFragment::class.simpleName
        val UID_KEY = "uid"
    }
    private lateinit var viewModel: UserProfileViewModel
    private lateinit var binding: UserProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = UserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GitHubService::class.java)
        val repo: UserRepository = UserRepository(service)


        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        val userId = arguments?.getString(UID_KEY) ?: ""
        // TODO Repositoryクラスは依存性の注入で渡す
        viewModel.init(repo, userId)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.upateButton.setOnClickListener {
        }
    }
}
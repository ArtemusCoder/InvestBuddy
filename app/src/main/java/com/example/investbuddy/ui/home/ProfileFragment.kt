package com.example.investbuddy.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.investbuddy.R
import com.example.investbuddy.data.UserPreferences
import com.example.investbuddy.data.network.Resource
import com.example.investbuddy.data.network.UserAPI
import com.example.investbuddy.data.repository.UserRepository
import com.example.investbuddy.data.responses.UserResponse
import com.example.investbuddy.databinding.ActivityHomeBinding
import com.example.investbuddy.databinding.FragmentProfileBinding
import com.example.investbuddy.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class ProfileFragment : BaseFragment<HomeViewModel, FragmentProfileBinding, UserRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    updateUI(it.value)
                }
                is Resource.Failure -> {

                }
            }
        })
    }

    private fun updateUI(user: UserResponse) {
        with(binding) {
            editTextEmail.text = user.email
            editTextUsername.text = user.username
            editTextRegisteredAt.text = user.registered_at

        }
    }


    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildAPI(UserAPI::class.java, token)
        return UserRepository(api)
    }

}
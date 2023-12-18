package com.example.investbuddy.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.investbuddy.R
import com.example.investbuddy.data.network.UserAPI
import com.example.investbuddy.data.repository.BaseRepository
import com.example.investbuddy.data.repository.UserRepository
import com.example.investbuddy.databinding.FragmentLoginBinding
import com.example.investbuddy.databinding.FragmentSettingsBinding
import com.example.investbuddy.ui.base.BaseFragment
import com.example.investbuddy.ui.base.BaseViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class SettingsFragment : BaseFragment<HomeViewModel, FragmentSettingsBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSettingsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() : UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildAPI(UserAPI::class.java, token)
        return UserRepository(api)
    }

}
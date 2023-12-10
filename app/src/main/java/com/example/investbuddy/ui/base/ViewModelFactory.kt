package com.example.investbuddy.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.investbuddy.data.repository.AuthRepository
import com.example.investbuddy.data.repository.BaseRepository
import com.example.investbuddy.data.repository.UserRepository
import com.example.investbuddy.ui.auth.AuthViewModel
import com.example.investbuddy.ui.home.HomeViewModel

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass not Found")
        }
    }
}
package com.example.investbuddy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.investbuddy.data.network.Resource
import com.example.investbuddy.data.repository.UserRepository
import com.example.investbuddy.data.responses.UserResponse
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: UserRepository
) : ViewModel() {

    private val _user: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val user: LiveData<Resource<UserResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = repository.getUser()
    }
}
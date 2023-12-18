package com.example.investbuddy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.investbuddy.data.network.Resource
import com.example.investbuddy.data.repository.UserRepository
import com.example.investbuddy.data.responses.StockResponse
import com.example.investbuddy.data.responses.UserResponse
import com.example.investbuddy.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val user: LiveData<Resource<UserResponse>>
        get() = _user

    private val _stocks: MutableLiveData<Resource<StockResponse>> = MutableLiveData()
    val stocks: LiveData<Resource<StockResponse>>
        get() = _stocks

    fun getUser() = viewModelScope.launch {
        _user.value = repository.getUser()
    }

    fun getMyStocks() = viewModelScope.launch {
        _stocks.value = repository.getStocks()
    }

    private val _balance: MutableLiveData<Resource<StockResponse>> = MutableLiveData()
    val balance: LiveData<Resource<StockResponse>>
        get() = _balance

    fun balanceAdd(
        money: Double
    ) = viewModelScope.launch {
        _balance.value = repository.balanceAdd(money)
    }
}
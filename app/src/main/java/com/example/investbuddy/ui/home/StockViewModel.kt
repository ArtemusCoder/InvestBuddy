package com.example.investbuddy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.investbuddy.data.network.Resource
import com.example.investbuddy.data.repository.StockRepository
import com.example.investbuddy.data.responses.StockResponse
import com.example.investbuddy.data.responses.StockResponseItem
import com.example.investbuddy.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class StockViewModel (
    private val repository: StockRepository
) : BaseViewModel(repository) {

    private val _stock: MutableLiveData<Resource<StockResponse>> = MutableLiveData()
    val stock: LiveData<Resource<StockResponse>>
        get() = _stock

    fun getStocks() = viewModelScope.launch {
        _stock.value = repository.getStocks()
    }

    private val _stock_item: MutableLiveData<Resource<StockResponseItem>> = MutableLiveData()
    val stock_item: LiveData<Resource<StockResponseItem>>
        get() = _stock_item

    fun getStock(
        id: Int
    ) = viewModelScope.launch {
        _stock_item.value = repository.getStock(id)
    }

    private val _stock_result: MutableLiveData<Resource<StockResponseItem>> = MutableLiveData()
    val stock_result: LiveData<Resource<StockResponseItem>>
        get() = _stock_result

    fun buyStocks(
        id: Int,
        quantity: Int
    ) = viewModelScope.launch {
        _stock_result.value = repository.buyStocks(id, quantity)
    }

    fun sellStocks(
        id: Int,
        quantity: Int
    ) = viewModelScope.launch {
        _stock_result.value = repository.sellStocks(id, quantity)
    }
}
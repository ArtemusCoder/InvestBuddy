package com.example.investbuddy.ui.home.card

import com.example.investbuddy.data.responses.StockResponseItem

interface StockClickListener {

    fun onClick(stock: StockResponseItem)
}
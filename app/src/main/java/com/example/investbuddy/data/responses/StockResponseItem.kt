package com.example.investbuddy.data.responses

data class StockResponseItem(
    val company: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val ticket: String,
    val quantity: Int
)
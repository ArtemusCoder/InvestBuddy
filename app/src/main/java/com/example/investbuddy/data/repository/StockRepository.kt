package com.example.investbuddy.data.repository

import com.example.investbuddy.data.network.StockAPI

class StockRepository(
    private val api: StockAPI,
) : BaseRepository() {

    suspend fun getStocks() = safeApiCall {
        api.getStocks()
    }

    suspend fun getStock(
        id: Int
    ) = safeApiCall {
        api.getStock(id)
    }

    suspend fun buyStocks(
        id: Int,
        quantity: Int
    ) = safeApiCall {
        api.buyStocks(id, quantity)
    }

    suspend fun sellStocks(
        id: Int,
        quantity: Int
    ) = safeApiCall {
        api.sellStocks(id, quantity)
    }

}
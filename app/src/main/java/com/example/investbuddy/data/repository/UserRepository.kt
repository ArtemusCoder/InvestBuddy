package com.example.investbuddy.data.repository

import com.example.investbuddy.data.network.UserAPI

class UserRepository(
    private val api: UserAPI,
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

    suspend fun getStocks() = safeApiCall {
        api.getMyStocks()
    }

    suspend fun balanceAdd(
        money: Double
    ) = safeApiCall {
        api.balance_add(money)
    }

}
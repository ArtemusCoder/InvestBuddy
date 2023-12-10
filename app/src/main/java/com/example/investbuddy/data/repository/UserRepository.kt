package com.example.investbuddy.data.repository

import com.example.investbuddy.data.network.UserAPI

class UserRepository(
    private val api: UserAPI,
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}
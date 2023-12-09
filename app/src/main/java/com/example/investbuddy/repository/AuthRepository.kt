package com.example.investbuddy.repository

import com.example.investbuddy.network.AuthAPI

class AuthRepository(
    private val api: AuthAPI
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

}
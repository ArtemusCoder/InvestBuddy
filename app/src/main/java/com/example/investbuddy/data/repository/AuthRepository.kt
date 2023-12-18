package com.example.investbuddy.data.repository

import com.example.investbuddy.data.UserPreferences
import com.example.investbuddy.data.network.AuthAPI
import com.example.investbuddy.ui.auth.RegisterBody

class AuthRepository(
    private val api: AuthAPI,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun register(
        registerBody: RegisterBody
    ) = safeApiCall {
        api.register(registerBody)
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

}
package com.example.investbuddy.data.repository

import com.example.investbuddy.data.UserPreferences
import com.example.investbuddy.data.network.AuthAPI

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

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

}
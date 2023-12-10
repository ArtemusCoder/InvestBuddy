package com.example.investbuddy.data.network

import com.example.investbuddy.data.responses.UserResponse
import retrofit2.http.GET

interface UserAPI {

    @GET("user")
    suspend fun getUser(): UserResponse
}
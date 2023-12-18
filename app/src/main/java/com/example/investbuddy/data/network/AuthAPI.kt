package com.example.investbuddy.data.network

import com.example.investbuddy.data.responses.LoginResponse
import com.example.investbuddy.data.responses.RegisterResponse
import com.example.investbuddy.ui.auth.RegisterBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded

interface AuthAPI {

    @FormUrlEncoded
    @POST("auth/jwt/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): LoginResponse

    @POST("auth/register")
    suspend fun register(
        @Body registerBody: RegisterBody?
    ): RegisterResponse
}
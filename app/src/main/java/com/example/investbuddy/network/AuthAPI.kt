package com.example.investbuddy.network

import com.example.investbuddy.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded

interface AuthAPI {

    @FormUrlEncoded
    @POST("auth/jwt/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ) : LoginResponse
}
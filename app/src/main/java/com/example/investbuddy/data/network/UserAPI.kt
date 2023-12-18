package com.example.investbuddy.data.network

import com.example.investbuddy.data.responses.StockResponse
import com.example.investbuddy.data.responses.UserResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {

    @GET("user")
    suspend fun getUser(): UserResponse

    @GET("my-stocks")
    suspend fun getMyStocks(): StockResponse

    @POST("auth/jwt/logout")
    suspend fun logout(): ResponseBody

    @POST("balance/add/{money}")
    suspend fun balance_add(
        @Path("money") money: Double
    ) : StockResponse


}
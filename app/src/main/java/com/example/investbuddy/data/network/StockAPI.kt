package com.example.investbuddy.data.network

import com.example.investbuddy.data.responses.StockResponse
import com.example.investbuddy.data.responses.StockResponseItem
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface StockAPI {

    @GET("stocks")
    suspend fun getStocks(): StockResponse

    @GET("stock/{id}")
    suspend fun getStock(
        @Path("id") id : Int
    ) : StockResponseItem

    @POST("stock/buy/{id}")
    suspend fun buyStocks(
        @Path("id") id: Int,
        @Query("quantity") quantity: Int
    ) : StockResponseItem

    @POST("stock/sell/{id}")
    suspend fun sellStocks(
        @Path("id") id: Int,
        @Query("quantity") quantity: Int
    ) : StockResponseItem
}
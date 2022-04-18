package com.odogwudev.stockmarketcompose.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET("query?function=LISTING__STATUS ")
    suspend fun getListings(
        @Query("apikey") apikey: String = API_KEY
    ): ResponseBody

    companion object {
        const val API_KEY = "7MNHGRS58U4N91BM"// source:aphavantage free key
        const val BASE_URL = "https://www.alphavantage.co/"
    }
}
package com.odogwudev.stockmarketcompose.data.remote

import com.odogwudev.stockmarketcompose.data.remote.dto.CompanyInfoDTO
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody


    companion object {
        const val API_KEY = "7MNHGRS58U4N91BM"
        const val BASE_URL = "https://alphavantage.co"
    }

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
         @Query("apikey") apiKey: String = API_KEY
    ):CompanyInfoDTO

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntroDayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody
}
package com.odogwudev.stockmarketcompose.domain.repository

import com.odogwudev.stockmarketcompose.domain.model.CompanyInfo
import com.odogwudev.stockmarketcompose.domain.model.CompanyListing
import com.odogwudev.stockmarketcompose.domain.model.IntraDayInfo
import com.odogwudev.stockmarketcompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntraDayInfo(
        symbol: String
    ): Resource<List<IntraDayInfo>>

    suspend fun getCompanyInfo(
        symbol: String,
    ): Resource<CompanyInfo>
}
package com.odogwudev.stockmarketcompose.domain.repository

import com.odogwudev.stockmarketcompose.domain.model.CompanyListingModel
import com.odogwudev.stockmarketcompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean = true, // save api request
        query: String
    ): Flow<Resource<List<CompanyListingModel>>>
}
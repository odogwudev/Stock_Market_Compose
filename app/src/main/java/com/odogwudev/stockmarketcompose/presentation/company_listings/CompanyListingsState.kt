package com.odogwudev.stockmarketcompose.presentation.company_listings

import com.odogwudev.stockmarketcompose.domain.model.CompanyListingModel
import com.odogwudev.stockmarketcompose.util.Resource

data class CompanyListingsState(
    val companies: List<CompanyListingModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)

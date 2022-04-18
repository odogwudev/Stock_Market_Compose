package com.odogwudev.stockmarketcompose.presentation.company_listings

sealed interface CompanyListingsEvent {
    object Refresh : CompanyListingsEvent
    data class OnSearchQueryChanged(val query: String) : CompanyListingsEvent
}
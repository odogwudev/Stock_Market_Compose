package com.odogwudev.stockmarketcompose.data.mapper

import com.odogwudev.stockmarketcompose.data.local.CompanyListingENtity
import com.odogwudev.stockmarketcompose.domain.model.CompanyListingModel

fun CompanyListingENtity.toCompanyListing(): CompanyListingModel {
    return CompanyListingModel(
        name = name,
        symbol = symbol,
        exchange = exchange,
    )
}

fun CompanyListingModel.toCompanyListingEntity(): CompanyListingENtity {
    return CompanyListingENtity(
        name = name,
        symbol = symbol,
        exchange = exchange,
    )
}
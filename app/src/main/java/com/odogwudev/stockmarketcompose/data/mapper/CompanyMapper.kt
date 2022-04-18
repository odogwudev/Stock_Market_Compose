package com.odogwudev.stockmarketcompose.data.mapper

import com.odogwudev.stockmarketcompose.data.local.CompanyListingEntity
import com.odogwudev.stockmarketcompose.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}
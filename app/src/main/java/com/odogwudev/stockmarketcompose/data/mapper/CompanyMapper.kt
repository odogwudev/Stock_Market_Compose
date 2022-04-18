package com.odogwudev.stockmarketcompose.data.mapper

import com.odogwudev.stockmarketcompose.data.local.CompanyListingEntity
import com.odogwudev.stockmarketcompose.data.remote.dto.CompanyInfoDto
import com.odogwudev.stockmarketcompose.domain.model.CompanyInfo
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

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}
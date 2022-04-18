package com.odogwudev.stockmarketcompose.data.mapper

import com.odogwudev.stockmarketcompose.data.local.CompanyListingEntity
import com.odogwudev.stockmarketcompose.data.remote.dto.CompanyInfoDTO
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

fun CompanyInfoDTO.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol
            ?: "",/// doing it this way (nullable because the limitation of free api is 5 calls a minute otherwise we get a different json representation saying exceeded the limit
        description = description ?: "",
        name = name ?: "", country = country ?: "", industry = industry ?: ""
    )
}
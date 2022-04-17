package com.odogwudev.stockmarketcompose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyListingENtity(
    val name: String,
    val symbol: String,
    val exchange: String,
    @PrimaryKey val id: Int? = null
)
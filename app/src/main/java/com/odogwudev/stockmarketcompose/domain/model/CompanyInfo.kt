package com.odogwudev.stockmarketcompose.domain.model

//i dont want to have moshi inside my domain layer
data class CompanyInfo(
    val symbol: String,
    val description: String, 
    val name: String,
    val country: String,
    val industry: String,  
)
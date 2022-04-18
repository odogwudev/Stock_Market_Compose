package com.odogwudev.stockmarketcompose.data.remote.dto

import com.squareup.moshi.Json

//kotlin repreesentation of json object
data class CompanyInfoDTO(
    @field:Json(name = "Symbol") val symbol: String?, // not using the capitalized Jsom
    @field:Json(name = "Description") val description: String?, // not using the capitalized Json
    @field:Json(name = "Name") val name: String?,// not using the capitalized Json
    @field:Json(name = "Country") val country: String?,// not using the capitalized Json
    @field:Json(name = "Industry") val industry: String?, // not using the capitalized Json
)
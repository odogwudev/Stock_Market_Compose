package com.odogwudev.stockmarketcompose.domain.model

import java.time.LocalDateTime

data class IntraDayInfo(
    val date: LocalDateTime, //using both day and time because we have the value of the date and time
    val close: Double
)

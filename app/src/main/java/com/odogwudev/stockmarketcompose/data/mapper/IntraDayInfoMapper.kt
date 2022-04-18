package com.odogwudev.stockmarketcompose.data.mapper

import com.odogwudev.stockmarketcompose.data.remote.dto.IntraDayDTO
import com.odogwudev.stockmarketcompose.domain.model.IntraDayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntraDayDTO.toIntraDayInfo(): IntraDayInfo {
    val pattern = "yyyy-MM-DD HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timeStamp, formatter)
    return IntraDayInfo(
        date = localDateTime,
        close = close
    )// dto object is not the same as domain level object
}
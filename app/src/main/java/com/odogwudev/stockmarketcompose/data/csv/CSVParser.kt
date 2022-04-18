package com.odogwudev.stockmarketcompose.data.csv

import java.io.InputStream

// Solid principle depend on abstraction not concretion

interface CSVParser<T> {
    suspend fun parse(stream: InputStream): List<T>
}
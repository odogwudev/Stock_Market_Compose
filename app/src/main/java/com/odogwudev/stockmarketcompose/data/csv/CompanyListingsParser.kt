package com.odogwudev.stockmarketcompose.data.csv

import com.odogwudev.stockmarketcompose.domain.model.CompanyListingModel
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton //only one of this should be created
class CompanyListingsParser @Inject constructor() : CSVParser<CompanyListingModel> {
    // constructor staying empty so daggger hilt knows the objects to create and provide it for other dependencies
    override suspend fun parse(stream: InputStream): List<CompanyListingModel> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)//first row doesn't contain company data
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(1)
                    val exchange = line.getOrNull(2)
                    CompanyListingModel(
                        name = name ?: return@mapNotNull null,
                        symbol = symbol ?: return@mapNotNull null,
                        exchange = exchange ?: return@mapNotNull null
                    )
                }
                .also {
                    csvReader.close( )
                }
        }
    }


}
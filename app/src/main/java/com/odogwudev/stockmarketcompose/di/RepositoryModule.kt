package com.odogwudev.stockmarketcompose.di

import com.odogwudev.stockmarketcompose.data.csv.CSVParser
import com.odogwudev.stockmarketcompose.data.csv.CompanyListingsParser
import com.odogwudev.stockmarketcompose.data.csv.IntradayInfoParser
import com.odogwudev.stockmarketcompose.data.repository.StockRepositoryImpl
import com.odogwudev.stockmarketcompose.domain.model.CompanyListing
import com.odogwudev.stockmarketcompose.domain.model.IntraDayInfo
import com.odogwudev.stockmarketcompose.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(companyListingsParser: CompanyListingsParser): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntraDayInfoParser(intradayInfoParser: IntradayInfoParser): CSVParser<IntraDayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(stockRepositoryImpl: StockRepositoryImpl): StockRepository
}
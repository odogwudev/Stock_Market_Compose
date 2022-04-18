package com.odogwudev.stockmarketcompose.data.repository

import com.odogwudev.stockmarketcompose.data.csv.CSVParser
import com.odogwudev.stockmarketcompose.data.csv.CompanyListingsParser
import com.odogwudev.stockmarketcompose.data.local.StockDatabase
import com.odogwudev.stockmarketcompose.data.mapper.toCompanyListing
import com.odogwudev.stockmarketcompose.data.mapper.toCompanyListingEntity
import com.odogwudev.stockmarketcompose.data.remote.StockApi
import com.odogwudev.stockmarketcompose.domain.model.CompanyListingModel
import com.odogwudev.stockmarketcompose.domain.repository.StockRepository
import com.odogwudev.stockmarketcompose.util.Resource
import com.opencsv.CSVReader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val companyListingsParser: CSVParser<CompanyListingModel>
) : StockRepository {

    private val dao = db.dao
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListingModel>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val localListings = dao.searchCompanyListing(query)
            emit(
                Resource.Success(
                    data = localListings.map {
                        it.toCompanyListing()
                    }
                )
            )
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
                ///     val cvsReader = CSVReader(InputStreamReader(response.byteStream()))// violates solid principle
                //     response.byteStream()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't Load the dat IO Exception "))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't Load Data"))
                null
            }

            remoteListings?.let { listings ->
                dao.cleaeCompanyListing()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() } // this line doesn't stick to the single principle of truth which means we only get dat from the database
                )
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))

            }
        }
    }

}
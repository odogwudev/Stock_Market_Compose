package com.odogwudev.stockmarketcompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListingEntities: List<CompanyListingENtity>
    )

    @Query("DELETE FROM  companylistingentity")
    suspend fun cleaeCompanyListing()

    @Query(
        """
        SELECT * FROM companylistingentity WHERE LOWER(name) LIKE '%' || LOWER(:query) ||  '%' OR UPPER(:query)==  symbol
    """
    )
    suspend fun searchCompanyListing(query: String): List<CompanyListingENtity>
}
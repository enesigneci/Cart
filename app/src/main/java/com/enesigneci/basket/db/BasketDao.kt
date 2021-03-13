package com.enesigneci.basket.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.enesigneci.basket.model.Listing

@Dao
interface BasketDao {
    @Query("SELECT * FROM listingitem")
    fun getAll(): List<Listing.ListingItem>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item: Listing.ListingItem)

    @Delete
    fun delete(item: Listing.ListingItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(item: Listing.ListingItem)
}
package com.enesigneci.basket.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.enesigneci.basket.model.Listing

@Dao
interface BasketDao {
    @Query("SELECT * FROM listingitem")
    fun getAll(): List<Listing.ListingItem>

    @Query("SELECT * FROM listingitem WHERE id IN (:listingIds)")
    fun loadAllByIds(listingIds: IntArray): List<Listing.ListingItem>

    @Insert
    fun insert(vararg item: Listing.ListingItem)

    @Delete
    fun delete(item: Listing.ListingItem)
}
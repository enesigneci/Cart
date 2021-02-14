package com.enesigneci.basket.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enesigneci.basket.App
import com.enesigneci.basket.model.Listing

object BasketDb {
    val db = Room.databaseBuilder(
        App.appContext,
        AppDatabase::class.java, "basket-db"
    ).build()

}
@Database(entities = arrayOf(Listing.ListingItem::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun basketDao(): BasketDao
}
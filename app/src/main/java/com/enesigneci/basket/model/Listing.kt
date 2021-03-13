package com.enesigneci.basket.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class Listing : ArrayList<Listing.ListingItem>(){
    @Entity
    data class ListingItem(
        @ColumnInfo(name = "currency")
        @SerializedName("currency")
        var currency: String,
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id")
        var id: Int,
        @ColumnInfo(name = "image")
        @SerializedName("image")
        var image: String,
        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String,
        @ColumnInfo(name = "price")
        @SerializedName("price")
        var price: String,
        @ColumnInfo(name = "quantity")
        var quantity: Int
    )
}
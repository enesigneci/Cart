package com.enesigneci.basket.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class Listing : ArrayList<Listing.ListingItem>(){
    @Entity
    data class ListingItem(
        @SerializedName("currency")
        var currency: String,
        @PrimaryKey
        @SerializedName("id")
        var id: Int,
        @SerializedName("image")
        var image: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("price")
        var price: String
    )
}
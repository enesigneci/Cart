package com.enesigneci.cart.model


import com.google.gson.annotations.SerializedName

class Listing : ArrayList<Listing.ListingItem>(){
    data class ListingItem(
        @SerializedName("currency")
        var currency: String,
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
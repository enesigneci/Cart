package com.enesigneci.basket.api

import com.enesigneci.basket.model.Listing
import com.enesigneci.basket.model.Order
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST


interface BasketService {
    @GET("listing")
    fun getListing(): Call<Listing>
    @POST("order")
    fun postOrder(@Body order: List<Order>): Call<Any>
}

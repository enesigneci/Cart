package com.enesigneci.basket.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    private const val BASE_URL = "https://nonchalant-fang.glitch.me/"

    private val gson = GsonBuilder().serializeNulls().create()

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BASE_URL)
                .build()
    }

    val basketService: BasketService by lazy {
        retrofit().create(BasketService::class.java)
    }

}
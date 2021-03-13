package com.enesigneci.basket.ui

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enesigneci.basket.api.Api
import com.enesigneci.basket.db.BasketDb
import com.enesigneci.basket.model.Listing
import com.enesigneci.basket.model.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketViewModel : ViewModel() {
    var listing: MutableLiveData<Listing> = MutableLiveData()
    var cart: MutableLiveData<List<Listing.ListingItem>> = MutableLiveData()
    var orderSent: MutableLiveData<Boolean> = MutableLiveData()
    fun getCart() {
        CoroutineScope(Dispatchers.IO).launch {
            cart.postValue(BasketDb.db.basketDao().getAll())
        }
    }

    fun clearCart() {
        CoroutineScope(Dispatchers.IO).launch {
            BasketDb.db.basketDao().clear()
            cart.postValue(BasketDb.db.basketDao().getAll())
        }
    }
    fun getListing() {
        Api.basketService.getListing().enqueue(object: Callback<Listing>{
            override fun onResponse(call: Call<Listing>, response: Response<Listing>) {
                response.let {
                    listing.value = it.body()
                }
            }

            override fun onFailure(call: Call<Listing>, t: Throwable) {

            }

        })
    }
    fun postOrder(orderList: List<Order>) {
        Api.basketService.postOrder(orderList).enqueue(object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    orderSent.postValue(true)
                } else {
                   orderSent.postValue(false)
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                orderSent.postValue(false)
            }
        })
    }
}
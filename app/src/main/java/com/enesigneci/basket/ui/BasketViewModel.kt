package com.enesigneci.basket.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enesigneci.basket.api.Api
import com.enesigneci.basket.db.BasketDb
import com.enesigneci.basket.model.Listing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketViewModel : ViewModel() {
    var listing: MutableLiveData<Listing> = MutableLiveData()
    var cart: MutableLiveData<List<Listing.ListingItem>> = MutableLiveData()
    fun getCart() {
        CoroutineScope(Dispatchers.IO).launch {
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
    fun postOrder(id: String, amount: Int) {
        Api.basketService.postOrder(id, amount)
    }
}
package com.enesigneci.basket.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enesigneci.basket.api.Api
import com.enesigneci.basket.model.Listing
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketViewModel : ViewModel() {
    var listing: MutableLiveData<Listing> = MutableLiveData()
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
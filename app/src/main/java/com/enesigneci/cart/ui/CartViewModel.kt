package com.enesigneci.cart.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enesigneci.cart.MyApi
import com.enesigneci.cart.model.Listing
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel : ViewModel() {
    var listing: MutableLiveData<Listing> = MutableLiveData()
    fun getListing() {
        MyApi.cartService.getListing().enqueue(object: Callback<Listing>{
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
        MyApi.cartService.postOrder(id, amount)
    }
}
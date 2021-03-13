package com.enesigneci.basket.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.enesigneci.basket.databinding.CartItemLayoutBinding
import com.enesigneci.basket.db.BasketDb
import com.enesigneci.basket.extensions.loadFromUrl
import com.enesigneci.basket.model.Listing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartAdapter(private var fragment: Fragment): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    var items: ArrayList<Listing.ListingItem> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        with(holder){
            with(items[position]) {
                binding.cartItemTitle.text = name
                binding.cartItemQuantity.text = quantity.toString()
                binding.cartItemImage.loadFromUrl(image)
                binding.cartItemPrice.text = price.plus(" ₺")
                binding.txtPlus.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        BasketDb.db.basketDao().update(items[position].apply { quantity++ })
                    }
                    updateItems(items)
                }
                binding.txtMinus.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        BasketDb.db.basketDao().update(items[position].apply { quantity-- })
                    }
                    updateItems(items)
                }
                binding.cartItemRemove.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        BasketDb.db.basketDao().delete(items[position])
                    }
                   updateItems(items)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateItems(list: ArrayList<Listing.ListingItem>) {
        this.items = list
        notifyDataSetChanged()
    }
    inner class CartViewHolder(val binding: CartItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
package com.enesigneci.basket.ui.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.enesigneci.basket.databinding.CartItemViewLayoutBinding
import com.enesigneci.basket.model.Listing

class ListingAdapter(private val onItemClickListener: View.OnClickListener) : RecyclerView.Adapter<ListingAdapter.ListingViewHolder>(){
    var items: ArrayList<Listing.ListingItem> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val binding = CartItemViewLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ListingViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        with(holder){
            with(items[position]) {
                binding.tvCartTitle.text = name
                binding.tvCartPrice.text = price.plus(" ₺")
                Glide.with(holder.itemView.context)
                    .load(image)
                    .into(binding.ivCartImage)
                binding.btnAddCart.setOnClickListener(onItemClickListener)
            }
        }
    }

    inner class ListingViewHolder(val binding: CartItemViewLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)

    fun updateItems(list: Listing) {
        this.items = list
        notifyDataSetChanged()
    }

}

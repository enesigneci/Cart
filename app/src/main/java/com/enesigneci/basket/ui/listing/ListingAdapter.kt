package com.enesigneci.basket.ui.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enesigneci.basket.databinding.ListingItemLayoutBinding
import com.enesigneci.basket.extensions.loadFromUrl
import com.enesigneci.basket.model.Listing

class ListingAdapter(private val onItemClickListener: View.OnClickListener) : RecyclerView.Adapter<ListingAdapter.ListingViewHolder>(){
    var items: ArrayList<Listing.ListingItem> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val binding = ListingItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ListingViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        with(holder){
            with(items[position]) {
                binding.tvCartTitle.text = name
                binding.tvCartPrice.text = price.plus(" ₺")
                binding.ivCartImage.loadFromUrl(image)
                binding.btnAddCart.setOnClickListener(onItemClickListener)
            }
        }
    }

    inner class ListingViewHolder(val binding: ListingItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)

    fun updateItems(list: Listing) {
        this.items = list
        notifyDataSetChanged()
    }

}

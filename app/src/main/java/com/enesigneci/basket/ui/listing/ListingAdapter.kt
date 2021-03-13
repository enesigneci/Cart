package com.enesigneci.basket.ui.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.enesigneci.basket.R
import com.enesigneci.basket.databinding.ListingItemLayoutBinding
import com.enesigneci.basket.db.BasketDb
import com.enesigneci.basket.extensions.loadFromUrl
import com.enesigneci.basket.model.Listing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListingAdapter(private var fragment: Fragment) : RecyclerView.Adapter<ListingAdapter.ListingViewHolder>(){
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
                binding.btnAddCart.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        BasketDb.db.basketDao().insert(items[position].apply { quantity++ })
                    }
                    fragment.findNavController().navigate(R.id.action_global_cartFragment)
                }
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

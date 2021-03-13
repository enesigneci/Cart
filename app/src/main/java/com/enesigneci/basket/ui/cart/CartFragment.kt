package com.enesigneci.basket.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesigneci.basket.databinding.FragmentCartBinding
import com.enesigneci.basket.model.Listing
import com.enesigneci.basket.ui.BasketViewModel


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val viewModel: BasketViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CartAdapter(this)
        binding.rvCart.adapter = adapter
        viewModel.getCart()
        binding.rvCart.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        update(adapter)
    }

    fun update(adapter: CartAdapter) {
        viewModel.cart.observe(viewLifecycleOwner, Observer {
            adapter.updateItems(it as ArrayList<Listing.ListingItem>)
        })
    }
}
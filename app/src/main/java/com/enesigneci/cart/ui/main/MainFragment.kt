package com.enesigneci.cart.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.enesigneci.cart.databinding.MainFragmentBinding
import com.enesigneci.cart.ui.CartViewModel

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListingAdapter({
           //TODO: call the add to basket method from viewModel
        })
        binding.rvListing.adapter = adapter
        viewModel.getListing()
        viewModel.listing.observe(viewLifecycleOwner, Observer {
            adapter.updateItems(it)
        })
        binding.rvListing.layoutManager = GridLayoutManager(context, 2)
    }

}
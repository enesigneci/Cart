package com.enesigneci.basket.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.enesigneci.basket.databinding.ListingFragmentBinding
import com.enesigneci.basket.ui.BasketViewModel

class ListingFragment : Fragment() {

    private lateinit var binding: ListingFragmentBinding

    companion object {
        fun newInstance() = ListingFragment()
    }

    private val viewModel: BasketViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = ListingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListingAdapter(this)
        binding.rvListing.adapter = adapter
        viewModel.getListing()
        viewModel.listing.observe(viewLifecycleOwner, Observer {
            adapter.updateItems(it)
        })
        binding.rvListing.layoutManager = GridLayoutManager(context, 2)
    }

}
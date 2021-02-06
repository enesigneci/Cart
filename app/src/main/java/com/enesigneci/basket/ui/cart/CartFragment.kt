package com.enesigneci.basket.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enesigneci.basket.R
import com.enesigneci.basket.databinding.CartItemLayoutBinding
import com.enesigneci.basket.databinding.FragmentCartBinding
import com.enesigneci.basket.databinding.ListingFragmentBinding


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }
}
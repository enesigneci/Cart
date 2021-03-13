package com.enesigneci.basket.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesigneci.basket.R
import com.enesigneci.basket.databinding.FragmentCartBinding
import com.enesigneci.basket.model.Listing
import com.enesigneci.basket.model.Order
import com.enesigneci.basket.ui.BasketViewModel


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val viewModel: BasketViewModel by viewModels()
    var orderList = mutableListOf<Order>()


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
        binding.btnContinueShopping.setOnClickListener {
            findNavController().navigate(R.id.action_global_listingFragment)
        }
        binding.btnPlaceOrder.setOnClickListener {
            viewModel.postOrder(orderList)
        }
        viewModel.orderSent.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.clearCart()
                Toast.makeText(context, "Your order is sent", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "There was an error while your order is processing", Toast.LENGTH_LONG).show()

            }
        })
    }

    fun update(adapter: CartAdapter) {
        viewModel.cart.observe(viewLifecycleOwner, Observer {
            binding.title.text = getString(R.string.my_cart, it.size)
            adapter.updateItems(it as ArrayList<Listing.ListingItem>)
            it.forEach {
                orderList.add(Order(it.id, it.quantity))
            }
        })
    }
}
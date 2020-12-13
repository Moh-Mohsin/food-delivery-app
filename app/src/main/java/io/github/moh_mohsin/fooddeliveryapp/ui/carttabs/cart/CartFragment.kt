package io.github.moh_mohsin.fooddeliveryapp.ui.carttabs.cart

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import io.github.moh_mohsin.fooddeliveryapp.R
import io.github.moh_mohsin.fooddeliveryapp.databinding.CartFragmentBinding
import io.github.moh_mohsin.fooddeliveryapp.util.toast
import io.github.moh_mohsin.fooddeliveryapp.util.viewBinding

class CartFragment : Fragment(R.layout.cart_fragment), MvRxView {

    private val viewModel: CartViewModel by fragmentViewModel()
    private val binding by viewBinding(CartFragmentBinding::bind)
    private val adapter by lazy { CartFoodListAdapter(viewModel::removeFromCart) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cartFoodList.adapter = adapter
        binding.cartFoodList.isNestedScrollingEnabled = true
    }

    override fun invalidate() = withState(viewModel) {
        when (it.cart) {
            Uninitialized -> {
            }
            is Loading -> {
            }
            is Success -> {
                TransitionManager.beginDelayedTransition(binding.root)
                adapter.submitList(it.cart()!!.items)
                binding.total.text = getString(R.string.price, it.total, "USD")
                if (it.cart()!!.items.isEmpty()) {
                    findNavController().popBackStack()
                }
            }
            is Fail -> {
                toast(it.cart.error.toString())
            }
        }
    }

}
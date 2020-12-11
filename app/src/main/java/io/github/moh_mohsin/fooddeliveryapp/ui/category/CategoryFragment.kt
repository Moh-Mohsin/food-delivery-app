package io.github.moh_mohsin.fooddeliveryapp.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.*
import io.github.moh_mohsin.fooddeliveryapp.R
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.databinding.CategoryFragmentBinding
import io.github.moh_mohsin.fooddeliveryapp.util.toast
import io.github.moh_mohsin.fooddeliveryapp.util.viewBinding

class CategoryFragment : Fragment(R.layout.category_fragment), MvRxView {

    private val viewModel: CategoryViewModel by fragmentViewModel()
    private val binding by viewBinding(CategoryFragmentBinding::bind)
    private val adapter = FoodListAdapter(::onAddToCart)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.foodList.adapter = adapter
    }

    private fun onAddToCart(food: Food){
        viewModel.addToCart(food)
    }

    override fun invalidate() = withState(viewModel){ state ->
        when (state.menu) {
            Uninitialized -> toast("Uninitialized")
            is Loading -> {
//                toast("Loading")
            }
            is Success -> {
                adapter.submitList(state.menu()!!)
            }
            is Fail -> toast("Fail")
        }
    }
}
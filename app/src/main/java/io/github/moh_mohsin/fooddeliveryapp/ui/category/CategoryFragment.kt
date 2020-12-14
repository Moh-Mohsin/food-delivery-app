package io.github.moh_mohsin.fooddeliveryapp.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.*
import io.github.moh_mohsin.fooddeliveryapp.R
import io.github.moh_mohsin.fooddeliveryapp.data.model.SubCategory
import io.github.moh_mohsin.fooddeliveryapp.databinding.CategoryFragmentBinding
import io.github.moh_mohsin.fooddeliveryapp.util.toast
import io.github.moh_mohsin.fooddeliveryapp.util.viewBinding

class CategoryFragment : Fragment(R.layout.category_fragment), MvRxView {

    private val viewModel: CategoryViewModel by fragmentViewModel()
    private val binding by viewBinding(CategoryFragmentBinding::bind)
    private val adapter by lazy { FoodListAdapter(viewModel::addToCart) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.foodList.adapter = adapter
        binding.foodList.isNestedScrollingEnabled = true

        binding.spicyChip.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked) {
                viewModel.addFilter(SubCategory.SPICY)
            } else {
                viewModel.removeFilter(SubCategory.SPICY)
            }
        }
        binding.veganChip.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked) {
                viewModel.addFilter(SubCategory.VEGAN)
            } else {
                viewModel.removeFilter(SubCategory.VEGAN)
            }
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        when (state.menu) {
            Uninitialized -> {
            }
            is Loading -> {
            }
            is Success -> {
                state.filters.forEach {
                    when (it) {
                        SubCategory.VEGAN -> {
                            binding.veganChip.isChecked = true
                        }
                        SubCategory.SPICY -> {
                            binding.spicyChip.isChecked = true
                        }
                    }
                }
                adapter.submitList(state.filteredMenu)
                if (state.filteredMenu.isEmpty()) {
                    toast(R.string.no_match_for_filters)
                }
            }
            is Fail -> toast("Failed")
        }
    }
}
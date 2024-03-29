package io.github.moh_mohsin.fooddeliveryapp.ui.menu

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.airbnb.mvrx.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.moh_mohsin.fooddeliveryapp.R
import io.github.moh_mohsin.fooddeliveryapp.data.model.MainCategory
import io.github.moh_mohsin.fooddeliveryapp.databinding.MenuFragmentBinding
import io.github.moh_mohsin.fooddeliveryapp.ui.category.CategoryFragment
import io.github.moh_mohsin.fooddeliveryapp.util.toast
import io.github.moh_mohsin.fooddeliveryapp.util.viewBinding
import java.util.*

class MenuFragment : Fragment(R.layout.menu_fragment), MavericksView {

    private val viewModel: MenuViewModel by fragmentViewModel()
    private val binding by viewBinding(MenuFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sliderAdapter = SliderAdapter()
        sliderAdapter.renewItems((1..3).map { SliderItem.SliderItemRes(R.drawable.supersale) })
        binding.imageSlider.setSliderAdapter(sliderAdapter)
    }

    override fun invalidate() = withState(viewModel) { state ->
        when (state.mainCategories) {
            Uninitialized -> {
            }
            is Loading -> {
                toast("loading...")
            } //TODO: show loading
            is Success -> {
                val categories = state.mainCategories()!!
                binding.tabLayout.apply {
                    removeAllTabs()
                    repeat(categories.size) {
                        addTab(newTab())
                    }
                    if (categories.size > 3) {
                        tabMode = TabLayout.MODE_AUTO
                    }
                }
                binding.viewPager2.adapter = PagerAdapter(this, categories)
                TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
                    tab.text = categories[position].toString().toLowerCase(Locale.ROOT).capitalize(
                        Locale.ROOT
                    )
                }.attach()
            }
            is Fail -> {
                toast(state.mainCategories.error.toString())
            } //TODO: handle
        }

    }

    private inner class PagerAdapter(fa: Fragment, private val mainCategories: List<MainCategory>) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = mainCategories.size

        override fun createFragment(position: Int): Fragment = CategoryFragment().apply {
            arguments = bundleOf(Mavericks.KEY_ARG to mainCategories[position])
        }
    }
}
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

class MenuFragment : Fragment(R.layout.menu_fragment), MvRxView {

    private val viewModel: MenuViewModel by fragmentViewModel()
    private val binding by viewBinding(MenuFragmentBinding::bind)

    override fun invalidate() = withState(viewModel){ state ->
        toast("${state.mainCategories}")
        when(state.mainCategories){
            Uninitialized -> {}
            is Loading -> {
                toast("loading...")
            } //TODO: show loading
            is Success -> {
                val categories = state.mainCategories()!!
                binding.tabLayout.apply {
                    removeAllTabs()
                    categories.forEach {
                        val newTab = newTab()
                        newTab.text = it.toString()
                        addTab(newTab)
                    }
                    if (categories.size > 3){
                        tabMode = TabLayout.MODE_AUTO
                    }
                }
                binding.viewPager2.adapter = PagerAdapter(this, categories)
                TabLayoutMediator(binding.tabLayout, binding.viewPager2){ tab, position ->
                    tab.text = categories[position].toString()
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
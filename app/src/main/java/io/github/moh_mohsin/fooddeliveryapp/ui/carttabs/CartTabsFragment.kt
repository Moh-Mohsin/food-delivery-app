package io.github.moh_mohsin.fooddeliveryapp.ui.carttabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import io.github.moh_mohsin.fooddeliveryapp.R
import io.github.moh_mohsin.fooddeliveryapp.databinding.CartTabsFragmentBinding
import io.github.moh_mohsin.fooddeliveryapp.ui.carttabs.cart.CartFragment
import io.github.moh_mohsin.fooddeliveryapp.ui.carttabs.info.InfoFragment
import io.github.moh_mohsin.fooddeliveryapp.ui.carttabs.orders.OrderFragment
import io.github.moh_mohsin.fooddeliveryapp.util.viewBinding

class CartTabsFragment : Fragment(R.layout.cart_tabs_fragment) {
    private val binding by viewBinding(CartTabsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.cart)
                1 -> getString(R.string.order)
                2 -> getString(R.string.information)
                else -> error("Only 3 fragments in cart menu")
            }
        }.attach()
    }

    private inner class PagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> CartFragment()
            1 -> OrderFragment()
            2 -> InfoFragment()
            else -> error("Only 3 fragments in cart menu")
        }
    }
}
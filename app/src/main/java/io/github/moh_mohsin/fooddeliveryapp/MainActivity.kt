package io.github.moh_mohsin.fooddeliveryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.viewModel
import com.airbnb.mvrx.withState
import com.andremion.counterfab.CounterFab
import io.github.moh_mohsin.fooddeliveryapp.MainScreen.CART_SCREEN
import io.github.moh_mohsin.fooddeliveryapp.MainScreen.FOOD_MENU_SCREEN
import io.github.moh_mohsin.fooddeliveryapp.util.getDrawableCompact
import io.github.moh_mohsin.fooddeliveryapp.util.toast

class MainActivity : AppCompatActivity(), MvRxView {

    private val viewModel: MainViewModel by viewModel()
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar))

        val fab = findViewById<CounterFab>(R.id.fab)

        fab.setOnClickListener { view ->
            when (navController.currentDestination?.id) {
                R.id.menuFragment -> {
                    navController.navigate(R.id.action_global_cartTabsFragment)
                }
                else -> {
                    toast("click")
                }
            }
        }


        viewModel.stateFlow.asLiveData().observe(this) {
            when (it.mainScreen) {
                FOOD_MENU_SCREEN -> {
                    fab.count = it.itemsInCart
                    fab.setImageDrawable(getDrawableCompact(R.drawable.ic_baseline_shopping_cart))
                }
                CART_SCREEN -> {
                    fab.count = 0
                    fab.setImageDrawable(getDrawableCompact(R.drawable.ic_baseline_credit_card))
                }
            }

        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.cartTabsFragment -> {
                    viewModel.setScreen(CART_SCREEN)
                }
                else -> {
                    viewModel.setScreen(FOOD_MENU_SCREEN)
                }
            }
        }
    }

    override fun invalidate() = withState(viewModel) {
        toast("MainActivity invalidate")
        //fixme: figure out why this is not triggered
    }
}
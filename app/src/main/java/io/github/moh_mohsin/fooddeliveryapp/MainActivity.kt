package io.github.moh_mohsin.fooddeliveryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.viewModel
import com.airbnb.mvrx.withState
import com.andremion.counterfab.CounterFab
import com.google.android.material.snackbar.Snackbar
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


//        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//        window.statusBarColor = Color.TRANSPARENT

        val fab = findViewById<CounterFab>(R.id.fab)

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

            fab.setOnClickListener { view ->
                when (it.mainScreen) {
                    FOOD_MENU_SCREEN -> {
                        if (it.itemsInCart > 0)
                            navController.navigate(R.id.action_global_cartTabsFragment)
                        else
                            Snackbar.make(fab, "Add items to Cart first", Snackbar.LENGTH_SHORT)
                                .show()
                    }
                    CART_SCREEN -> {
                        toast("Payment...")
                    }
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
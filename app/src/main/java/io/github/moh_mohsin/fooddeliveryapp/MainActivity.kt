package io.github.moh_mohsin.fooddeliveryapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.viewModel
import com.airbnb.mvrx.withState
import com.andremion.counterfab.CounterFab
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
        setSupportActionBar(findViewById(R.id.toolbar))

        val fab = findViewById<CounterFab>(R.id.fab)

        fab.setOnClickListener { view ->
            when (navController.currentDestination?.id) {
                R.id.menuFragment -> {
                    navController.navigate(R.id.cartTabsFragment)
                }
                else -> {
                    toast("click")
                }
            }
        }

        val bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet_card))

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
//                    BottomSheetBehavior.STATE_DRAGGING -> {
//                        toast("STATE_DRAGGING")
//                    }
//                    BottomSheetBehavior.STATE_SETTLING -> {
//                        toast("STATE_COLLAPSED")
//                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        toast("STATE_EXPANDED")
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        toast("STATE_COLLAPSED")
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                toast("$slideOffset")
            }
        })

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun invalidate() = withState(viewModel) {
        toast("MainActivity invalidate")
        //fixme: figure out why this is not triggered
    }
}
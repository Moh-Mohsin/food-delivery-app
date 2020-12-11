package io.github.moh_mohsin.fooddeliveryapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
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
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

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
        //fixme: figure out why this is not triggered
    }
}
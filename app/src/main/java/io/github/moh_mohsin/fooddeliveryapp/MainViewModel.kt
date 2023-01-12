package io.github.moh_mohsin.fooddeliveryapp

import com.airbnb.mvrx.*
import io.github.moh_mohsin.fooddeliveryapp.data.model.Cart
import io.github.moh_mohsin.fooddeliveryapp.data.repository.CartRepository
import io.github.moh_mohsin.fooddeliveryapp.di.KodeinInjector
import org.kodein.di.erased.instance

enum class MainScreen {
    FOOD_MENU_SCREEN,
    CART_SCREEN
}

data class MainState(
    val itemsInCart: Int = 0,
    val mainScreen: MainScreen = MainScreen.FOOD_MENU_SCREEN
) : MavericksState

class MainViewModel(state: MainState, cartRepository: CartRepository) :
    MavericksViewModel<MainState>(state) {

    init {
        cartRepository.getCart()
//            .observeOn(Schedulers.io())
            .execute { cart: Async<Cart> ->
                copy(itemsInCart = cart()?.items?.size ?: itemsInCart)
            }
    }

    fun setScreen(mainScreen: MainScreen) {
        setState { copy(mainScreen = mainScreen) }
    }

    companion object : MavericksViewModelFactory<MainViewModel, MainState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: MainState
        ): MainViewModel {
            val cartRepository by KodeinInjector.instance<CartRepository>()
            return MainViewModel(state, cartRepository)
        }
    }
}
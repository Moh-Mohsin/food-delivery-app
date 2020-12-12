package io.github.moh_mohsin.fooddeliveryapp.ui.carttabs.cart

import com.airbnb.mvrx.*
import io.github.moh_mohsin.fooddeliveryapp.data.model.Cart
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.data.repository.CartRepository
import io.github.moh_mohsin.fooddeliveryapp.di.KodeinInjector
import io.reactivex.schedulers.Schedulers
import org.kodein.di.erased.instance

data class CartState(val cart: Async<Cart> = Uninitialized) : MavericksState {
    val total: Double
        get() = cart()?.items?.sumByDouble { it.first.price * it.second } ?: 0.0
}

class CartViewModel(state: CartState, private val cartRepository: CartRepository) :
    BaseMvRxViewModel<CartState>(state) {
    // TODO: Implement the ViewModel

    init {
        cartRepository.getCart()
            .observeOn(Schedulers.io())
            .execute { cart: Async<Cart> ->
                copy(cart = cart)
            }
    }

    fun removeFromCart(food: Food) {
        cartRepository.removeFromCart(food)
    }

    companion object : MavericksViewModelFactory<CartViewModel, CartState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: CartState
        ): CartViewModel {
            val cartRepository by KodeinInjector.instance<CartRepository>()
            return CartViewModel(state, cartRepository)
        }
    }
}
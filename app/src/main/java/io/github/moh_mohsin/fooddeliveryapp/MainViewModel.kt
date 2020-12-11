package io.github.moh_mohsin.fooddeliveryapp

import com.airbnb.mvrx.*
import io.github.moh_mohsin.fooddeliveryapp.data.model.Cart
import io.github.moh_mohsin.fooddeliveryapp.data.repository.CartRepository
import io.github.moh_mohsin.fooddeliveryapp.di.KodeinInjector
import io.reactivex.schedulers.Schedulers
import org.kodein.di.erased.instance

data class MainState(val itemsInCart: Int = 0) : MavericksState
class MainViewModel(state: MainState, cartRepository: CartRepository) :
    BaseMvRxViewModel<MainState>(state) {

    init {
        cartRepository.getCart()
            .observeOn(Schedulers.io())
            .execute { cart: Async<Cart> ->
                setState { copy() }
                copy(itemsInCart = cart()?.items?.size ?: itemsInCart)
            }
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
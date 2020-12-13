package io.github.moh_mohsin.fooddeliveryapp.data.repository.impl

import io.github.moh_mohsin.fooddeliveryapp.data.model.Cart
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.data.repository.CartRepository
import io.github.moh_mohsin.fooddeliveryapp.data.source.CartDataSource
import io.reactivex.Observable

class CartRepositoryImpl(private val cartDataSource: CartDataSource) : CartRepository {

    override fun getCart(): Observable<Cart> {
        return cartDataSource.getCart()
    }

    override fun addToCart(food: Food): Cart {
        return cartDataSource.addToCart(food)
    }

    override fun removeFromCart(food: Food): Cart {
        return cartDataSource.removeFromCart(food)
    }

}
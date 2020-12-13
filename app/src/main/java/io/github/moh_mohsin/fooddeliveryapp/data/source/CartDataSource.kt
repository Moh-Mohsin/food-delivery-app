package io.github.moh_mohsin.fooddeliveryapp.data.source

import io.github.moh_mohsin.fooddeliveryapp.data.model.Cart
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.reactivex.Observable

interface CartDataSource {

    fun getCart(): Observable<Cart>

    fun addToCart(food: Food): Cart

    fun removeFromCart(food: Food): Cart
}
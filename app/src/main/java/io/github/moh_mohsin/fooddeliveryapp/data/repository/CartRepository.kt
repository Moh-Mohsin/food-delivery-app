package io.github.moh_mohsin.fooddeliveryapp.data.repository

import io.github.moh_mohsin.fooddeliveryapp.data.model.Cart
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.reactivex.Observable

interface CartRepository {

    fun getCart(): Observable<Cart>

    fun addToCart(food: Food): Cart

    fun removeFromCart(food: Food): Cart
}
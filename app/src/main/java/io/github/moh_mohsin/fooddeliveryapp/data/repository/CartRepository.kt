package io.github.moh_mohsin.fooddeliveryapp.data.repository

import io.github.moh_mohsin.fooddeliveryapp.data.model.Cart
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCart(): Flow<Cart>

    suspend fun addToCart(food: Food): Cart

    suspend fun removeFromCart(food: Food): Cart
}
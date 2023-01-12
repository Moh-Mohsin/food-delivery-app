package io.github.moh_mohsin.fooddeliveryapp.data.repository.impl

import io.github.moh_mohsin.fooddeliveryapp.data.model.Cart
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.data.repository.CartRepository
import io.github.moh_mohsin.fooddeliveryapp.data.source.CartDataSource
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(private val cartDataSource: CartDataSource) : CartRepository {

    override fun getCart(): Flow<Cart> {
        return cartDataSource.getCart()
    }

    override suspend fun addToCart(food: Food): Cart {
        return cartDataSource.addToCart(food)
    }

    override suspend fun removeFromCart(food: Food): Cart {
        return cartDataSource.removeFromCart(food)
    }

}
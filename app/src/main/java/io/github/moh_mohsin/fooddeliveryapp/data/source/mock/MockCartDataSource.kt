package io.github.moh_mohsin.fooddeliveryapp.data.source.mock

import io.github.moh_mohsin.fooddeliveryapp.data.model.Cart
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.data.source.CartDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class MockCartDataSource : CartDataSource {

    private val items = mutableMapOf<Food, Int>()
    private val itemsFlow = MutableSharedFlow<Cart>()

    override fun getCart(): Flow<Cart> {
        return itemsFlow
    }

    override suspend fun addToCart(food: Food): Cart {
        items[food] = (items[food] ?: 0) + 1
        val cart = Cart(items.map { it.key to it.value })
        itemsFlow.emit(cart)
        return cart
    }

    override suspend fun removeFromCart(food: Food): Cart {
        items.remove(food)
        val cart = Cart(items.map { it.key to it.value })
        itemsFlow.emit(cart)
        return cart
    }

}
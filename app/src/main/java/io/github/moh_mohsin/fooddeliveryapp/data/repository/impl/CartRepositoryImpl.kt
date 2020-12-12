package io.github.moh_mohsin.fooddeliveryapp.data.repository.impl

import io.github.moh_mohsin.fooddeliveryapp.data.model.Cart
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.data.repository.CartRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class CartRepositoryImpl : CartRepository {
    private val items = mutableMapOf<Food, Int>()
    private val itemsSubject = BehaviorSubject.create<Cart>()

    override fun getCart(): Observable<Cart> {
        return itemsSubject.observeOn(Schedulers.io())
    }

    override fun addToCart(food: Food): Cart {
        items[food] = items[food] ?: 0 + 1
        val cart = Cart(items.map { it.key to it.value })
        itemsSubject.onNext(cart)
        return cart
    }

    override fun removeFromCart(food: Food): Cart {
        items.remove(food)
        val cart = Cart(items.map { it.key to it.value })
        itemsSubject.onNext(cart)
        return cart
    }


}
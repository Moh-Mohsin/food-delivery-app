package io.github.moh_mohsin.fooddeliveryapp.data.repository

import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.reactivex.Observable

interface FoodRepository {
    fun getMenu(): Observable<List<Food>>
}
package io.github.moh_mohsin.fooddeliveryapp.data.source

import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.reactivex.Observable

interface FoodDataSource {
    fun getMenu(): Observable<List<Food>>
}
package io.github.moh_mohsin.fooddeliveryapp.data.source

import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodDataSource {
    fun getMenu(): Flow<List<Food>>
}
package io.github.moh_mohsin.fooddeliveryapp.data.repository

import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getMenu(): Flow<List<Food>>
}
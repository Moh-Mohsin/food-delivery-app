package io.github.moh_mohsin.fooddeliveryapp.data.repository.impl

import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.data.repository.FoodRepository
import io.github.moh_mohsin.fooddeliveryapp.data.source.FoodDataSource
import kotlinx.coroutines.flow.Flow

class FoodRepositoryImpl(private val foodDataSource: FoodDataSource) : FoodRepository {
    override fun getMenu(): Flow<List<Food>> {
        return foodDataSource.getMenu()
    }
}
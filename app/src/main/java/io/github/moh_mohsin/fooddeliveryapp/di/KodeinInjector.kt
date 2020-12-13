package io.github.moh_mohsin.fooddeliveryapp.di

import io.github.moh_mohsin.fooddeliveryapp.data.repository.CartRepository
import io.github.moh_mohsin.fooddeliveryapp.data.repository.FoodRepository
import io.github.moh_mohsin.fooddeliveryapp.data.repository.impl.CartRepositoryImpl
import io.github.moh_mohsin.fooddeliveryapp.data.repository.impl.FoodRepositoryImpl
import io.github.moh_mohsin.fooddeliveryapp.data.source.CartDataSource
import io.github.moh_mohsin.fooddeliveryapp.data.source.FoodDataSource
import io.github.moh_mohsin.fooddeliveryapp.data.source.mock.MockCartDataSource
import io.github.moh_mohsin.fooddeliveryapp.data.source.mock.MockFoodDataSource
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val KodeinInjector = Kodein {
    bind<FoodRepository>() with singleton { FoodRepositoryImpl(instance()) }
    bind<CartRepository>() with singleton { CartRepositoryImpl(instance()) }

    bind<FoodDataSource>() with singleton { MockFoodDataSource() }
    bind<CartDataSource>() with singleton { MockCartDataSource() }
}
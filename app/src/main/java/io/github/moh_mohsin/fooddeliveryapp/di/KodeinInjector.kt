package io.github.moh_mohsin.fooddeliveryapp.di

import io.github.moh_mohsin.fooddeliveryapp.data.repository.CartRepository
import io.github.moh_mohsin.fooddeliveryapp.data.repository.FoodRepository
import io.github.moh_mohsin.fooddeliveryapp.data.repository.impl.CartRepositoryImpl
import io.github.moh_mohsin.fooddeliveryapp.data.repository.impl.FoodRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

val KodeinInjector = Kodein {
    bind<FoodRepository>() with singleton { FoodRepositoryImpl() }
    bind<CartRepository>() with singleton { CartRepositoryImpl() }
}
package io.github.moh_mohsin.fooddeliveryapp.data.model

data class Cart(
    val items: List<Pair<Food, Int>>
)
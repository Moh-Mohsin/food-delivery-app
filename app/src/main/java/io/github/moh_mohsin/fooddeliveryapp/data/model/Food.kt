package io.github.moh_mohsin.fooddeliveryapp.data.model

data class Food(
    val id: Int,
    val name: String,
    val ingredients: String, //TODO: put ingredients in a separate class?
    val size: String,
    val mainCategory: MainCategory,
    val subCategories: List<SubCategory>,
    val image: String,
    val price: Double,
)
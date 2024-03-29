package io.github.moh_mohsin.fooddeliveryapp.data.source.mock

import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.data.model.MainCategory
import io.github.moh_mohsin.fooddeliveryapp.data.model.SubCategory
import io.github.moh_mohsin.fooddeliveryapp.data.source.FoodDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockFoodDataSource : FoodDataSource {
    override fun getMenu(): Flow<List<Food>> = flow {
        //TODO: put some delay
        emit(dummyMenu)
    }
}

var id = 0
val dummyMenu = listOf(
    Food(
        id++,
        "Hawaiian",
        "Chicken, Mozarella, Pineapple, Domino's sauce",
        "150 grams, 35 cm",
        MainCategory.PIZZA,
        listOf(),
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/pizza-hut-thin-n-crispy-hawaiian-chicken-1538502865.jpg",
        55.00
    ),
    Food(
        id++,
        "Deluxe",
        "Chicken, ham, pepperoni, tomato sauce, spicy chorizo and mazzarella",
        "150 grams, 35 cm",
        MainCategory.PIZZA,
        listOf(SubCategory.SPICY),
        "https://www.tokyoweekender.com/wp-content/uploads/2015/03/SpicyDeluxe_L_13R04.png",
        46.00
    ),
    Food(
        id++,
        "Pepperoni",
        "Moarella, Peperoni, Tomatoes, BBQ sauce",
        "150 grams, 35 cm",
        MainCategory.PIZZA,
        listOf(SubCategory.VEGAN), //not sure if this is vegan but well.. gotta test this
        "https://static.onecms.io/wp-content/uploads/sites/19/2014/07/10/pepperoni-pizza-ck-x.jpg",
        55.00
    ),
    Food(
        id++,
        "The egoist",
        "Fila classic, maci spice-salmon, tow sushi salmon, two guangana salami",
        "450 grams, 18 piece",
        MainCategory.SUSHI,
        listOf(),
        "https://s2.narvii.com/image/jcsn7scu7jslgqqfobqh6bnw75htclx3_hq.jpg",
        45.00
    ),
    Food(
        id++,
        "California",
        "California with salmon frill, fila with salmon, fila killo-rossa, futomaki perch cheese, maki spicy salmon",
        "930 grams, 38 pieces",
        MainCategory.SUSHI,
        listOf(),
        "https://i1.wp.com/www.angsarap.net/wp-content/uploads/2018/02/California-Maki-Wide.jpg?fit=1080%2C720&ssl=1",
        60.00
    ),
    Food(
        id++,
        "Spiced Apple cider",
        "pure apple juice, cinnamon sticks, whole cloves, whole allspice berries",
        "350 ml",
        MainCategory.DRINKS,
        listOf(SubCategory.SPICY),
        "https://www.faithfullyglutenfree.com/wp-content/uploads/2014/12/Apple-Cider-0806.jpg",
        35.00
    ),
    Food(
        id++,
        "Lemon Juice",
        "lemon juice concentrate, lemon oil, sodium benzoate",
        "350 ml",
        MainCategory.DRINKS,
        listOf(),
        "https://cdn2.stylecraze.com/wp-content/uploads/2013/09/2310_10-Best-Benefits-Of-Lemon-Juice-For-Skin-Hair-And-Health_iS.jpg_1.jpg",
        25.00
    ),
)
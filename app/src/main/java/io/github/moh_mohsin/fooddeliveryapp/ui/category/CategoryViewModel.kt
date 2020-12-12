package io.github.moh_mohsin.fooddeliveryapp.ui.category

import com.airbnb.mvrx.*
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.data.model.MainCategory
import io.github.moh_mohsin.fooddeliveryapp.data.model.SubCategory
import io.github.moh_mohsin.fooddeliveryapp.data.repository.CartRepository
import io.github.moh_mohsin.fooddeliveryapp.data.repository.FoodRepository
import io.github.moh_mohsin.fooddeliveryapp.di.KodeinInjector
import io.reactivex.schedulers.Schedulers
import org.kodein.di.erased.instance

data class CategoryState(
    val menu: Async<List<Food>> = Uninitialized,
    val filters: Set<SubCategory> = setOf(),
) : MavericksState {
    val filteredMenu: List<Food>
        get() {
            return if (filters.isEmpty())
                menu() ?: listOf()
            else
                menu()?.filter {
                    filters.all { subCat ->
                        subCat in it.subCategories
                    }
                } ?: listOf()
        }
}

class CategoryViewModel(
    state: CategoryState,
    mainCategory: MainCategory,
    foodRepository: FoodRepository,
    private val cartRepository: CartRepository
) :
    BaseMvRxViewModel<CategoryState>(state) {

    init {
        foodRepository.getMenu()
            .subscribeOn(Schedulers.io())
            .map { it.filter { food -> food.mainCategory == mainCategory } }
            .execute { copy(menu = it) }
    }

    fun addToCart(food: Food) {
        cartRepository.addToCart(food)
    }

    fun addFilter(subCategory: SubCategory) {
        setState {
            copy(filters = filters + subCategory)
        }
    }

    fun removeFilter(subCategory: SubCategory) {
        setState {
            copy(filters = filters - subCategory)
        }
    }

    companion object : MavericksViewModelFactory<CategoryViewModel, CategoryState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: CategoryState
        ): CategoryViewModel {
            val mainCategory = viewModelContext.args<MainCategory>()
            val foodRepository by KodeinInjector.instance<FoodRepository>()
            val cartRepository by KodeinInjector.instance<CartRepository>()
            return CategoryViewModel(state, mainCategory, foodRepository, cartRepository)
        }
    }
}
package io.github.moh_mohsin.fooddeliveryapp.ui.menu

import android.util.Log
import com.airbnb.mvrx.*
import io.github.moh_mohsin.fooddeliveryapp.data.model.MainCategory
import io.github.moh_mohsin.fooddeliveryapp.data.repository.FoodRepository
import io.github.moh_mohsin.fooddeliveryapp.di.KodeinInjector
import kotlinx.coroutines.flow.map
import org.kodein.di.erased.instance

data class MenuState(val mainCategories: Async<List<MainCategory>> = Uninitialized) : MavericksState

class MenuViewModel(menuState: MenuState, foodRepository: FoodRepository) :
    MavericksViewModel<MenuState>(menuState) {

    init {
        Log.d("MenuViewModel", "init")
        foodRepository.getMenu()
            .map { it.map { food -> food.mainCategory }.distinct() }
//            .subscribeOn(Schedulers.io())
            .execute {
                Log.d("MenuViewModel", "Collecting flow...")
                copy(mainCategories = it)
            }
    }

    companion object : MavericksViewModelFactory<MenuViewModel, MenuState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: MenuState
        ): MenuViewModel {
            val foodRepository by KodeinInjector.instance<FoodRepository>()
            return MenuViewModel(state, foodRepository)
        }
    }
}
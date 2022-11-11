package com.matikano.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.util.UiEvent
import com.matikano.core.util.extension.capitalizeEnum
import com.matikano.core_ui.navigation.Screen
import com.matikano.tracker_domain.use_case.tracker_overview.TrackerOverviewUseCases
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor (
    private val useCases: TrackerOverviewUseCases
): ViewModel() {

    var state by mutableStateOf(TrackerOverviewState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodsForDateJob: Job? = null

    init {
        refreshFoods()
    }

    fun onEvent(event: TrackerOverviewEvent) {
        when(event) {
            is TrackerOverviewEvent.OnAddFoodClick -> viewModelScope.launch {
                _uiEvent.send(
                    UiEvent.Navigate(
                        route = Screen.Search.navigationRoute
                                + "/${event.meal.mealType.name.capitalizeEnum()}"
                                + "/${state.date.dayOfMonth}"
                                + "/${state.date.monthValue}"
                                + "/${state.date.year}"
                    )
                )
            }
            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> viewModelScope.launch {
                useCases.deleteTrackedFood(event.trackedFood)
                refreshFoods(collapseMeals = false)
            }
            is TrackerOverviewEvent.OnNextDayClick -> {
                state = state.copy(
                    date = state.date.plusDays(1)
                )
                refreshFoods()
            }


            is TrackerOverviewEvent.OnPreviousDayClick -> {
                state = state.copy(
                    date = state.date.minusDays(1)
                )
                refreshFoods()
            }

            is TrackerOverviewEvent.OnToggleMealClick ->
                state = state.copy(
                    meals = state.meals.map { meal ->
                        if (meal.name == event.meal.name) {
                            meal.copy(expanded = !meal.expanded)
                        } else meal
                    }
                )
            TrackerOverviewEvent.OnDialogClose ->
                state = state.copy(
                    dialogOpen = false
                )

            TrackerOverviewEvent.OnInfoButtonClick ->
                state = state.copy(
                    dialogOpen = true
                )
        }
    }

    private fun refreshFoods(collapseMeals: Boolean = true){
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = useCases
            .getFoodsForDate(state.date)
            .onEach { foods ->
                val nutrientsResult = useCases.calculateMealNutrients(foods)
                state = state.copy(
                    totalCarbs = nutrientsResult.totalCarbs,
                    totalProtein = nutrientsResult.totalProtein,
                    totalFat = nutrientsResult.totalFat,
                    totalCalories = nutrientsResult.totalCalories,
                    carbsGoal = nutrientsResult.carbsGoal,
                    proteinGoal = nutrientsResult.proteinGoal,
                    fatGoal = nutrientsResult.fatGoal,
                    caloriesGoal = nutrientsResult.caloriesGoal,
                    trackedFoods = foods,
                    meals = state.meals.map { meal ->
                        val nutrientsForMeal =
                            nutrientsResult.mealNutrients[meal.mealType]
                                ?: return@map meal.copy(
                                    carbs = 0,
                                    protein = 0,
                                    fat = 0,
                                    calories = 0,
                                    expanded = if(collapseMeals) false else meal.expanded
                                )
                        meal.copy(
                            carbs = nutrientsForMeal.carbs,
                            protein = nutrientsForMeal.protein,
                            fat = nutrientsForMeal.fat,
                            calories = nutrientsForMeal.calories,
                            expanded = if(collapseMeals) false else meal.expanded
                        )
                    }
                )
            }.launchIn(viewModelScope)
    }
}
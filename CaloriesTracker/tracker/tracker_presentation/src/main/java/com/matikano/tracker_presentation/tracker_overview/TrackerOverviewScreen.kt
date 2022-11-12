package com.matikano.tracker_presentation.tracker_overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.matikano.core.util.UiEvent
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.core.R
import com.matikano.tracker_presentation.compontnts.DisplayAlertDialog
import com.matikano.tracker_presentation.tracker_overview.components.*

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    backToOnBoarding: () -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    DisplayAlertDialog(
        title = stringResource(id = R.string.reinit_dialog_title),
        message = stringResource(id = R.string.reinit_dialog_message),
        onCancelClick = {
            viewModel.onEvent(TrackerOverviewEvent.OnDialogClose)
        },
        onConfirmClick = {
            viewModel.onEvent(TrackerOverviewEvent.OnDialogClose)
            backToOnBoarding()
        },
        isOpen = state.dialogOpen
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(
                state = state,
                onEvent = viewModel::onEvent
            )
            DaySelector(
                date = state.date,
                onEvent = viewModel::onEvent
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        items(state.meals) { meal ->
            ExpandableMeal(
                meal = meal,
                onEvent = viewModel::onEvent,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceSmall),
                ) {
                    state.trackedFoods
                        .filter { food ->
                            food.mealType == meal.mealType
                        }
                        .forEach { trackedFood ->
                            TrackedFoodItem(
                                trackedFood = trackedFood,
                                onEvent = viewModel::onEvent
                            )
                            Spacer(modifier = Modifier.height(spacing.spaceMedium))
                        }
                    AddMealButton(
                        text = stringResource(
                            id = R.string.add_meal,
                            meal.name.asString(context)
                        ),
                        meal = meal,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}
package com.matikano.onboarding_presentation.nutrients_goal

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.matikano.core.R
import com.matikano.core.util.UiEvent
import com.matikano.core_ui.LocalSpacing
import com.matikano.onboarding_presentation.components.OnBoardingTopBar
import com.matikano.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun NutrientsGoalScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: NutrientsGoalViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.message.asString(context)
                    )
                }
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            OnBoardingTopBar(
                onNavigateBackClicked = {
                    viewModel.onEvent(NutrientsGoalEvent.OnNavigateBackClicked)
                },
                title = stringResource(id = R.string.nutrient_goal)
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .absoluteOffset(y = -spacing.topAppBarHeight),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.what_are_your_nutrient_goals),
                    style = MaterialTheme.typography.h3
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                UnitTextField(
                    value = state.carbs,
                    onValueChange = { carbs ->
                        viewModel.onEvent(NutrientsGoalEvent.OnCarbsChanged(carbs))
                    },
                    unit = stringResource(id = R.string.percent_carbs)
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                UnitTextField(
                    value = state.protein,
                    onValueChange = { protein ->
                        viewModel.onEvent(NutrientsGoalEvent.OnProteinChanged(protein))
                    },
                    unit = stringResource(id = R.string.percent_proteins)
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                UnitTextField(
                    value = state.fat,
                    onValueChange = { fat ->
                        viewModel.onEvent(NutrientsGoalEvent.OnFatChanged(fat))
                    },
                    unit = stringResource(id = R.string.percent_fats)
                )
            }
            Button(
                onClick = {
                    viewModel.onEvent(NutrientsGoalEvent.OnNextClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = stringResource(id = R.string.next).uppercase(),
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(spacing.spaceSmall)
                )
            }
        }
    }
}
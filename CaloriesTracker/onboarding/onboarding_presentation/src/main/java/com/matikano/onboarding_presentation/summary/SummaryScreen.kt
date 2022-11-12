package com.matikano.onboarding_presentation.summary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.matikano.core.util.UiEvent
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.core.R
import com.matikano.onboarding_presentation.components.OnBoardingTopBar
import com.matikano.onboarding_presentation.summary.components.*
import kotlinx.coroutines.flow.collect

@Composable
fun SummaryScreen(
    navigateToFeature: () -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: SummaryViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            OnBoardingTopBar(
                onNavigateBackClicked = {
                    viewModel.onEvent(SummaryEvent.OnNavigateBackClick)
                },
                title = stringResource(id = R.string.summary)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .absoluteOffset(y = -spacing.spaceMedium),
                text = stringResource(id = R.string.personal_info),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .absoluteOffset(y = -spacing.topAppBarHeight)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceSmall),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GenderInfo(gender = state.userInfo.gender)
                    AgeInfo(age = state.userInfo.age)
                    HeightInfo(height = state.userInfo.height)
                    WeightInfo(weight = state.userInfo.weight)
                    GoalTypeInfo(goalType = state.userInfo.goalType)
                    ActivityLevelInfo(level = state.userInfo.activityLevel)
                    NutrientsGoalInfo(
                        carbsRatio = state.userInfo.carbRatio,
                        proteinRatio = state.userInfo.proteinRatio,
                        fatRatio = state.userInfo.fatRatio
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButton(
                    onClick = {
                        viewModel.onEvent(SummaryEvent.OnNavigateBackClick)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    border = BorderStroke(
                        width = ButtonDefaults.outlinedBorder.width,
                        color = MaterialTheme.colors.primary
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.back).uppercase(),
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(spacing.spaceSmall)
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Button(
                    onClick = {
                        viewModel.onEvent(SummaryEvent.OnConfirmClick)
                        navigateToFeature()
                    },
                    modifier = Modifier
                        .fillMaxWidth(),

                    ) {
                    Text(
                        text = stringResource(id = R.string.confirm).uppercase(),
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(spacing.spaceSmall)
                    )
                }
            }
        }
    }
}
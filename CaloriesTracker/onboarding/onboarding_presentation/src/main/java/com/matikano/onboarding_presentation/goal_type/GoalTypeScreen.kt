package com.matikano.onboarding_presentation.goal_type

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.matikano.core.R
import com.matikano.core.domain.model.GoalType
import com.matikano.core.util.UiEvent
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.onboarding_presentation.components.OnBoardingTopBar
import com.matikano.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun GoalScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: GoalTypeViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
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
                    viewModel.onEvent(GoalTypeEvent.OnNavigateBackClick)
                },
                title = stringResource(id = R.string.goal_type)
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
                    text = stringResource(id = R.string.lose_keep_or_gain_weight),
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                FlowRow (
                    modifier = Modifier
                        .fillMaxWidth(),
                    mainAxisAlignment = FlowMainAxisAlignment.Center,
                    mainAxisSpacing = spacing.spaceSmall,
                    crossAxisAlignment = FlowCrossAxisAlignment.Center,
                    crossAxisSpacing = spacing.spaceMedium
                ) {
                    SelectableButton(
                        text = stringResource(id = R.string.lose),
                        isSelected = state.goalType == GoalType.LOSE_WEIGHT,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedColor = Color.White,
                        onClick = {
                            viewModel.onEvent(GoalTypeEvent.OnGoalTypeClickType(GoalType.LOSE_WEIGHT))
                        },
                        icon = Icons.Default.SouthEast
                    )
                    SelectableButton(
                        text = stringResource(id = R.string.keep),
                        isSelected = state.goalType == GoalType.KEEP_WEIGHT,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedColor = Color.White,
                        onClick = {
                            viewModel.onEvent(GoalTypeEvent.OnGoalTypeClickType(GoalType.KEEP_WEIGHT))
                        },
                        icon = Icons.Default.HorizontalRule
                    )
                    SelectableButton(
                        text = stringResource(id = R.string.gain),
                        isSelected = state.goalType == GoalType.GAIN_WEIGHT,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedColor = Color.White,
                        onClick = {
                            viewModel.onEvent(GoalTypeEvent.OnGoalTypeClickType(GoalType.GAIN_WEIGHT))
                        },
                        icon = Icons.Default.NorthEast
                    )
                }
            }
            Button(
                onClick = {
                    viewModel.onEvent(GoalTypeEvent.OnNextClick)
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
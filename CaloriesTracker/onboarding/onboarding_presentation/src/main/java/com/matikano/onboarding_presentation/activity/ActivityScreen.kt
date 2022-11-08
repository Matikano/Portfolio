package com.matikano.onboarding_presentation.activity

import android.app.ActionBar
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.matikano.core.R
import com.matikano.core.domain.model.ActivityLevel
import com.matikano.core.util.UiEvent
import com.matikano.core_ui.LocalSpacing
import com.matikano.onboarding_presentation.components.OnBoardingTopBar
import com.matikano.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun ActivityScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: ActivityViewModel = hiltViewModel()
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
                    viewModel.onEvent(ActivityEvent.OnNavigateBackClick)
                },
                title = stringResource(id = R.string.activity_level)
            )
        },
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
                    text = stringResource(id = R.string.whats_your_activity_level),
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Row {
                    SelectableButton(
                        text = stringResource(id = R.string.low),
                        isSelected = state.activityLevel == ActivityLevel.LOW,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedColor = Color.White,
                        onClick = {
                            viewModel.onEvent(ActivityEvent.OnActivityClick(ActivityLevel.LOW))
                        },
                        icon = Icons.Default.Elderly
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    SelectableButton(
                        text = stringResource(id = R.string.medium),
                        isSelected = state.activityLevel == ActivityLevel.MEDIUM,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedColor = Color.White,
                        onClick = {
                            viewModel.onEvent(ActivityEvent.OnActivityClick(ActivityLevel.MEDIUM))
                        },
                        icon = Icons.Default.DirectionsWalk
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    SelectableButton(
                        text = stringResource(id = R.string.high),
                        isSelected = state.activityLevel == ActivityLevel.HIGH,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedColor = Color.White,
                        onClick = {
                            viewModel.onEvent(ActivityEvent.OnActivityClick(ActivityLevel.HIGH))
                        },
                        icon = Icons.Default.DirectionsRun
                    )
                }
            }
            Button(
                onClick = {
                    viewModel.onEvent(ActivityEvent.OnNextClick)
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
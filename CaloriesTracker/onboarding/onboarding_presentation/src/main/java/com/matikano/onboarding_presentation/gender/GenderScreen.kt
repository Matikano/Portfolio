package com.matikano.onboarding_presentation.gender

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.matikano.core.util.UiEvent
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.core.R
import com.matikano.core.domain.model.Gender
import com.matikano.onboarding_presentation.components.OnBoardingTopBar
import com.matikano.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun GenderScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: GenderViewModel = hiltViewModel()
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
                    viewModel.onEvent(GenderEvent.OnNavigateBackClick)
                },
                title = stringResource(id = R.string.gender)
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
                    text = stringResource(id = R.string.whats_your_gender),
                    style = MaterialTheme.typography.h3
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Row {
                    SelectableButton(
                        text = stringResource(id = R.string.male),
                        isSelected = state.gender == Gender.MALE,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedColor = Color.White,
                        onClick = {
                            viewModel.onEvent(GenderEvent.OnGenderClicked(Gender.MALE))
                        },
                        icon = Icons.Default.Male
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    SelectableButton(
                        text = stringResource(id = R.string.female),
                        isSelected = state.gender == Gender.FEMALE,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedColor = Color.White,
                        onClick = {
                            viewModel.onEvent(GenderEvent.OnGenderClicked(Gender.FEMALE))
                        },
                        icon = Icons.Default.Female
                    )
                }
            }
            Button(
                onClick = {
                    viewModel.onEvent(GenderEvent.OnNextClick)
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
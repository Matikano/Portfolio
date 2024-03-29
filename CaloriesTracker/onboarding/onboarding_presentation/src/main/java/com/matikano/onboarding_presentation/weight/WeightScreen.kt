package com.matikano.onboarding_presentation.weight

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
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.onboarding_presentation.components.OnBoardingTopBar
import com.matikano.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun WeightScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: WeightViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
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
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            OnBoardingTopBar(
                onNavigateBackClicked = { viewModel.onEvent(WeightEvent.OnNavigateBack) },
                title = stringResource(id = R.string.weight)
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
                    text = stringResource(id = R.string.whats_your_weight),
                    style = MaterialTheme.typography.h3
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                UnitTextField(
                    value = state.weight,
                    onValueChange = { weight ->
                        viewModel.onEvent(WeightEvent.OnWeightChange(weight))
                    },
                    unit = stringResource(id = R.string.kg)
                )
            }
            Button(
                onClick = {
                    viewModel.onEvent(WeightEvent.OnNextClick)
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
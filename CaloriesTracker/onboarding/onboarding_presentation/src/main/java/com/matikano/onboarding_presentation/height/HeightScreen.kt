package com.matikano.onboarding_presentation.height

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
fun HeightScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: HeightViewModel = hiltViewModel(),
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
                onNavigateBackClicked = {
                    viewModel.onEvent(HeightEvent.OnNavigateBackClick)
                },
                title = stringResource(id = R.string.height)
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
                    text = stringResource(id = R.string.whats_your_height),
                    style = MaterialTheme.typography.h3
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                UnitTextField(
                    value = state.height,
                    onValueChange = { height ->
                        viewModel.onEvent(HeightEvent.OnHeightChanged(height))
                    },
                    unit = stringResource(id = R.string.cm)
                )
            }
            Button(
                onClick = {
                    viewModel.onEvent(HeightEvent.OnNextClick)
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
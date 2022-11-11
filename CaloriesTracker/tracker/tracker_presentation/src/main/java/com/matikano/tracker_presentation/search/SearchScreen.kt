package com.matikano.tracker_presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.matikano.core.util.UiEvent
import com.matikano.core.R
import com.matikano.core_ui.LocalSpacing
import com.matikano.tracker_domain.model.MealType
import com.matikano.tracker_presentation.search.components.SearchTextField
import com.matikano.tracker_presentation.search.components.SearchTopBar
import com.matikano.tracker_presentation.search.components.TrackableFoodItem
import java.time.LocalDate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState,
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    viewModel: SearchViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.state
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = keyboardController) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackBar ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.message.asString(context)
                    ).also {
                        keyboardController?.hide()
                    }
                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            SearchTopBar(
                mealName = mealName,
                onEvent = viewModel::onEvent
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium)
        ) {
            SearchTextField(
                text = state.query,
                onEvent = viewModel::onEvent,
                shouldShowHint = state.isHintVisible
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.trackableFoods) { food ->
                    TrackableFoodItem(
                        trackableFoodUiState = food,
                        date = LocalDate.of(year, month, dayOfMonth),
                        mealType = MealType.valueOf(mealName.uppercase()),
                        onEvent = viewModel::onEvent,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){
            when {
                state.isSearching -> CircularProgressIndicator()
                state.trackableFoods.isEmpty() ->
                    Text(
                        text = stringResource(id = R.string.no_results),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )
            }
        }
    }
}
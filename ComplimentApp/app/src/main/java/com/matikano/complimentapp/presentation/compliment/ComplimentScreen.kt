package com.matikano.complimentapp.presentation.compliment

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.matikano.complimentapp.presentation.compliment.components.Compliment
import com.matikano.complimentapp.presentation.compliment.components.SettingsButton
import com.matikano.complimentapp.presentation.ui.theme.gradients
import com.matikano.complimentapp.presentation.ui.util.toGradient
import com.matikano.complimentapp.presentation.util.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComplimentScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ComplimentViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect { event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar -> scaffoldState.snackbarHostState.showSnackbar(event.content)
                else -> Unit
            }
        }
    }

    var background by remember {
        mutableStateOf(gradients.random().toGradient())
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
        onRefresh = {
            viewModel.onEvent(ComplimentEvent.OnRefresh)
            background = gradients.random().toGradient()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background),
        ){
            SettingsButton(onSettingsClicked = onNavigate)
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ){
                state.compliment?.let { compliment ->
                    Compliment(
                        modifier = Modifier.align(Alignment.Center),
                        compliment = compliment
                    )
                }
                if(state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.error?.let { error ->
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = error,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}
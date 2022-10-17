package com.matikano.complimentapp.presentation.compliment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.matikano.complimentapp.presentation.compliment.components.Compliment
import com.matikano.complimentapp.presentation.ui.theme.gradients
import com.matikano.complimentapp.presentation.ui.util.toGradient

@Composable
fun ComplimentScreen(
    viewModel: ComplimentViewModel = hiltViewModel()
) {

    val state = viewModel.state

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
        onRefresh = {
            viewModel.onEvent(ComplimentEvent.OnRefresh)
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            state.compliment?.let { compliment ->
                Compliment(
                    modifier = Modifier
                        .align(Alignment.Center),
                    compliment = compliment
                )
            }
            if(state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            state.error?.let { error ->
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                )
            }
        }



    }


}
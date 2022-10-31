package com.matikano.complimentapp.presentation.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.matikano.complimentapp.presentation.settings.components.SettingsContent
import com.matikano.complimentapp.presentation.settings.components.SettingsFloatingActionButton
import com.matikano.complimentapp.presentation.settings.components.SettingsTopBar
import com.matikano.complimentapp.presentation.ui.theme.gradients
import com.matikano.complimentapp.presentation.ui.util.toGradient
import com.matikano.complimentapp.presentation.util.UiEvent
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController,
    showSnackBar: (String) -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val background = remember {
        gradients.random().toGradient()
    }
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect { event ->
            when(event){
                is UiEvent.PopBackStack -> navController.popBackStack()
                else -> Unit
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .background(background),
        backgroundColor = Color.Transparent,
        topBar = {
           SettingsTopBar(
               state = state,
               showSnackBar = showSnackBar,
               onEvent = viewModel::onEvent
           )
        },
        floatingActionButton = {
            SettingsFloatingActionButton(
                onEvent = viewModel::onEvent
            )
        },
        content = {
           SettingsContent(
               state = state,
               onEvent = viewModel::onEvent
           )
        }
    )
}
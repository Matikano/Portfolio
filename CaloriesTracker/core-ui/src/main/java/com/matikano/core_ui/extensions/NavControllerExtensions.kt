package com.matikano.core_ui.extensions

import androidx.navigation.NavController
import com.matikano.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) = navigate(event.route)
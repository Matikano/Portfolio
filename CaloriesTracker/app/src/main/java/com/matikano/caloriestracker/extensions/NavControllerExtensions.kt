package com.matikano.caloriestracker.extensions

import androidx.navigation.NavController
import com.matikano.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) = navigate(event.route)
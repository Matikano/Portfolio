package com.matikano.complimentapp.util.extenstions

import androidx.navigation.NavController
import com.matikano.complimentapp.util.UiEvent


fun NavController.navigate(event: UiEvent.Navigate) = navigate(event.route)
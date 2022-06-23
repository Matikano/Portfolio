package com.gmail.matikano9.mobilebankappui.presentation.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gmail.matikano9.mobilebankappui.ui.theme.Surface
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AccountsScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Surface),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Accounts and credit cards")
    }
}
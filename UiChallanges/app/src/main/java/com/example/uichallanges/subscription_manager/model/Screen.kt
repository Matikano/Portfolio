package com.example.uichallanges.subscription_manager.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.ViewDay
import androidx.compose.material.icons.outlined.DashboardCustomize
import androidx.compose.material.icons.outlined.ViewDay
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(
    val selectedIcon: ImageVector,
    val notSelectedIcon: ImageVector,
    val label: String,
) {
    object General: Screen(
        selectedIcon = Icons.Filled.DashboardCustomize,
        notSelectedIcon = Icons.Outlined.DashboardCustomize,
        label = "General"
    )

    object MySubs: Screen(
        selectedIcon = Icons.Filled.ViewDay,
        notSelectedIcon = Icons.Outlined.ViewDay,
        label = "My Subs"
    )
}

val allScreens = listOf(
    Screen.General,
    Screen.MySubs
)


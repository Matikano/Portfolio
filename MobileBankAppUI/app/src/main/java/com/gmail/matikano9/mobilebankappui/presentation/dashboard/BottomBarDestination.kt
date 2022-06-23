package com.gmail.matikano9.mobilebankappui.presentation.dashboard

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.gmail.matikano9.mobilebankappui.R
import com.gmail.matikano9.mobilebankappui.presentation.destinations.AccountsScreenDestination
import com.gmail.matikano9.mobilebankappui.presentation.destinations.DashboardScreenDestination
import com.gmail.matikano9.mobilebankappui.presentation.destinations.PaymentsScreenDestination
import com.gmail.matikano9.mobilebankappui.presentation.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Dashboard(DashboardScreenDestination, Icons.Outlined.Home, R.string.home_label),
    Payments(PaymentsScreenDestination, Icons.Outlined.AccountBalanceWallet, R.string.payments_label),
    Dummy(AccountsScreenDestination, Icons.Default.Add, R.string.empty),
    Accounts(AccountsScreenDestination, Icons.Outlined.List, R.string.accounts_label),
    Settings(SettingsScreenDestination, Icons.Outlined.Settings, R.string.settings_label)
}
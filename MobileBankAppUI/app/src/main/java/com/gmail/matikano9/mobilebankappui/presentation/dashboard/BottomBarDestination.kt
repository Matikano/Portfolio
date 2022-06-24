package com.gmail.matikano9.mobilebankappui.presentation.dashboard

import androidx.annotation.DrawableRes
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
    val direction: DirectionDestinationSpec?,
    @DrawableRes val icon: Int?,
    @StringRes val label: Int
) {
    Dashboard(DashboardScreenDestination, R.drawable.ic_home, R.string.home_label),
    Payments(PaymentsScreenDestination, R.drawable.ic_wallet, R.string.payments_label),
    Dummy(null, null, R.string.empty),
    Accounts(AccountsScreenDestination, R.drawable.ic_list, R.string.accounts_label),
    Settings(SettingsScreenDestination, R.drawable.ic_settings, R.string.settings_label)
}
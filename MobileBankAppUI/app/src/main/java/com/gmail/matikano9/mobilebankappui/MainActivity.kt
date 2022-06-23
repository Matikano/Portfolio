package com.gmail.matikano9.mobilebankappui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Payments
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.gmail.matikano9.mobilebankappui.presentation.NavGraphs
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.DashboardScreen
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardAdd
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardBottomNavBar
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardTopAppBar
import com.gmail.matikano9.mobilebankappui.ui.theme.BasicGradient
import com.gmail.matikano9.mobilebankappui.ui.theme.MobileBankAppUITheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileBankAppUITheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier,
                    topBar = {
                        DashboardTopAppBar()
                    },
                    bottomBar = {
                        DashboardBottomNavBar(navController = navController)
                    },
                    floatingActionButton = {
                        IconButton(
                            modifier = Modifier
                                .background(
                                    brush = BasicGradient,
                                    shape = RoundedCornerShape(50),
                                ),
                            onClick = { 
                                
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Payments,
                                contentDescription = stringResource(id = R.string.fab_payments),
                                tint = Color.White,
                            )
                        }
                    },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.Center,
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController
                    )
                    it
                }
            }
        }
    }
}

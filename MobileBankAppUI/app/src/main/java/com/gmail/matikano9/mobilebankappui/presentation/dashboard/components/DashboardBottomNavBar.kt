package com.gmail.matikano9.mobilebankappui.presentation.dashboard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gmail.matikano9.mobilebankappui.presentation.NavGraphs
import com.gmail.matikano9.mobilebankappui.presentation.appCurrentDestinationAsState
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.BottomBarDestination
import com.gmail.matikano9.mobilebankappui.presentation.destinations.Destination
import com.gmail.matikano9.mobilebankappui.presentation.startAppDestination
import com.gmail.matikano9.mobilebankappui.ui.theme.AlegreyaSans
import com.gmail.matikano9.mobilebankappui.ui.theme.BasicGradient
import com.gmail.matikano9.mobilebankappui.ui.theme.RedGrad
import com.gmail.matikano9.mobilebankappui.ui.theme.Shapes
import com.gmail.matikano9.mobilebankappui.utils.Extensions.setGradient
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.navigateTo

@Composable
fun DashboardBottomNavBar(
    navController: NavController
) {

    BottomAppBar(
        modifier = Modifier,
        elevation = AppBarDefaults.BottomAppBarElevation,
        cutoutShape = RoundedCornerShape(50),
        backgroundColor = Color.White,
    ) {

            val currentDestination: Destination? = navController.appCurrentDestinationAsState().value
                ?: NavGraphs.root.startAppDestination

            BottomBarDestination.values().forEach { destination ->
                if(destination == BottomBarDestination.Dummy){
                    Spacer(modifier = Modifier.weight(1f))
                } else {
                    BottomNavigationItem(
                        modifier = Modifier.weight(1f),
                        selected = currentDestination == destination.direction,
                        alwaysShowLabel = false,
                        onClick = {
                            navController.navigate(destination.direction!!) {

                            }
                        },
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .setGradient(
                                        BasicGradient,
                                        currentDestination == destination.direction
                                    ),
                                painter = painterResource(id = destination.icon!!),
                                contentDescription = stringResource(id = destination.label),
                            )
                        },
                        label = {
                            Text(
                                modifier = Modifier
                                    .setGradient(
                                        BasicGradient,
                                        currentDestination == destination.direction
                                    ),
                                text = stringResource(id = destination.label),
                                fontFamily = AlegreyaSans,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    )
                }
            }
        }

}
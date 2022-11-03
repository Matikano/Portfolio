package com.matikano.caloriestracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matikano.caloriestracker.extensions.navigate
import com.matikano.caloriestracker.ui.theme.CaloriesTrackerTheme
import com.matikano.core.navigation.Screens
import com.matikano.onboarding_presentation.activity.ActivityScreen
import com.matikano.onboarding_presentation.age.AgeScreen
import com.matikano.onboarding_presentation.gender.GenderScreen
import com.matikano.onboarding_presentation.goal.GoalScreen
import com.matikano.onboarding_presentation.height.HeightScreen
import com.matikano.onboarding_presentation.weight.WeightScreen
import com.matikano.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloriesTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screens.WELCOME
                    ){
                        composable(Screens.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Screens.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(Screens.AGE) {
                            AgeScreen(
                                onNavigate = navController::navigate,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Screens.HEIGHT) {
                            HeightScreen(
                                onNavigate = navController::navigate,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Screens.WEIGHT) {
                            WeightScreen(
                                onNavigate = navController::navigate,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Screens.GOAL) {
                            GoalScreen(onNavigate = navController::navigate)
                        }
                        composable(Screens.ACTIVITY) {
                            ActivityScreen(onNavigate = navController::navigate)
                        }
                        composable(Screens.NUTRIENT_GOAL) {

                        }
                        composable(Screens.SUMMARY) {

                        }
                        composable(Screens.TRACKER_OVERVIEW) {

                        }
                        composable(Screens.SEARCH) {

                        }
                    }
                }
            }
        }
    }
}

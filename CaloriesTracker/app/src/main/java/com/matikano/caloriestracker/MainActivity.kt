package com.matikano.caloriestracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matikano.caloriestracker.extensions.navigate
import com.matikano.caloriestracker.ui.theme.CaloriesTrackerTheme
import com.matikano.core.domain.preferences.Preferences
import com.matikano.core.navigation.Screens
import com.matikano.onboarding_presentation.activity.ActivityScreen
import com.matikano.onboarding_presentation.age.AgeScreen
import com.matikano.onboarding_presentation.gender.GenderScreen
import com.matikano.onboarding_presentation.goal_type.GoalScreen
import com.matikano.onboarding_presentation.height.HeightScreen
import com.matikano.onboarding_presentation.nutrients_goal.NutrientsGoalScreen
import com.matikano.onboarding_presentation.summary.SummaryScreen
import com.matikano.onboarding_presentation.weight.WeightScreen
import com.matikano.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloriesTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val onBoarding = preferences.needsOnboarding()
                NavHost(
                    navController = navController,
                    startDestination = if(onBoarding) {
                        Screens.WELCOME
                    } else Screens.TRACKER_OVERVIEW
                ){
                    composable(Screens.WELCOME) {
                        WelcomeScreen(onNavigate = navController::navigate)
                    }
                    composable(Screens.GENDER) {
                        GenderScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack
                        )
                    }
                    composable(Screens.AGE) {
                        AgeScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                            scaffoldState = scaffoldState
                        )
                    }
                    composable(Screens.HEIGHT) {
                        HeightScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                            scaffoldState = scaffoldState
                        )
                    }
                    composable(Screens.WEIGHT) {
                        WeightScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                            scaffoldState = scaffoldState
                        )
                    }
                    composable(Screens.GOAL) {
                        GoalScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                        )
                    }
                    composable(Screens.ACTIVITY) {
                        ActivityScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                        )
                    }
                    composable(Screens.NUTRIENT_GOAL) {
                        NutrientsGoalScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                            scaffoldState = scaffoldState
                        )
                    }
                    composable(Screens.SUMMARY) {
                        SummaryScreen(
                            navigateToFeature = {
                                navController.navigate(Screens.TRACKER_OVERVIEW)
                            },
                            onPopBackStack = navController::popBackStack
                        )
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

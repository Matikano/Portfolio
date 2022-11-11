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
import com.matikano.core_ui.navigation.Screen
import com.matikano.onboarding_presentation.activity.ActivityScreen
import com.matikano.onboarding_presentation.age.AgeScreen
import com.matikano.onboarding_presentation.gender.GenderScreen
import com.matikano.onboarding_presentation.goal_type.GoalScreen
import com.matikano.onboarding_presentation.height.HeightScreen
import com.matikano.onboarding_presentation.nutrients_goal.NutrientsGoalScreen
import com.matikano.onboarding_presentation.summary.SummaryScreen
import com.matikano.onboarding_presentation.weight.WeightScreen
import com.matikano.onboarding_presentation.welcome.WelcomeScreen
import com.matikano.tracker_presentation.search.SearchScreen
import com.matikano.tracker_presentation.tracker_overview.TrackerOverviewScreen
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
                val onBoarding = preferences.loadOnBoarding()

                NavHost(
                    navController = navController,
                    startDestination = if(onBoarding) {
                        Screen.Welcome.route
                    } else Screen.TrackerOverview.route
                ){
                    composable(Screen.Welcome.route) {
                        WelcomeScreen(onNavigate = navController::navigate)
                    }
                    composable(Screen.Gender.route) {
                        GenderScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack
                        )
                    }
                    composable(Screen.Age.route) {
                        AgeScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                            scaffoldState = scaffoldState
                        )
                    }
                    composable(Screen.Height.route) {
                        HeightScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                            scaffoldState = scaffoldState
                        )
                    }
                    composable(Screen.Weight.route) {
                        WeightScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                            scaffoldState = scaffoldState
                        )
                    }
                    composable(Screen.Goal.route) {
                        GoalScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                        )
                    }
                    composable(Screen.Activity.route) {
                        ActivityScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                        )
                    }
                    composable(Screen.NutrientsGoal.route) {
                        NutrientsGoalScreen(
                            onNavigate = navController::navigate,
                            onPopBackStack = navController::popBackStack,
                            scaffoldState = scaffoldState
                        )
                    }
                    composable(Screen.Summary.route) {
                        SummaryScreen(
                            navigateToFeature = {
                                navController.navigate(Screen.TrackerOverview.navigationRoute) {
                                    popUpTo(Screen.Welcome.navigationRoute) {
                                        inclusive = true
                                    }
                                }
                            },
                            onPopBackStack = navController::popBackStack
                        )
                    }
                    composable(Screen.TrackerOverview.route) {
                        TrackerOverviewScreen(
                            onNavigate = navController::navigate,
                            backToOnBoarding = {
                                navController.navigate(Screen.Welcome.navigationRoute) {
                                    popUpTo(Screen.TrackerOverview.navigationRoute) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable(
                        route = Screen.Search.route,
                        arguments = Screen.Search.navArgs,
                    )
                    {
                        val navArgs = it.arguments!!
                        val mealName = navArgs.getString(Screen.NavArgs.Search.NAV_ARG_MEAL_NAME)!!
                        val dayOfMonth = navArgs.getInt(Screen.NavArgs.Search.NAV_ARG_DAY_OF_MONTH)
                        val month = navArgs.getInt(Screen.NavArgs.Search.NAV_ARG_MONTH)
                        val year = navArgs.getInt(Screen.NavArgs.Search.NAV_ARG_YEAR)
                        SearchScreen(
                            scaffoldState = scaffoldState,
                            mealName = mealName,
                            dayOfMonth = dayOfMonth,
                            month = month,
                            year = year,
                            onPopBackStack = navController::popBackStack
                        )
                    }
                }
            }
        }

    }
}

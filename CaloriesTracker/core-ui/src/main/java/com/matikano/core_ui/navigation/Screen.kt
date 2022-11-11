package com.matikano.core_ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.matikano.core_ui.navigation.Screen.NavArgs.Search.NAV_ARG_DAY_OF_MONTH
import com.matikano.core_ui.navigation.Screen.NavArgs.Search.NAV_ARG_MEAL_NAME
import com.matikano.core_ui.navigation.Screen.NavArgs.Search.NAV_ARG_MONTH
import com.matikano.core_ui.navigation.Screen.NavArgs.Search.NAV_ARG_YEAR


sealed class Screen(
    val route: String,
    val navArgs: List<NamedNavArgument> = emptyList()
) {
    val navigationRoute: String =
        if(navArgs.isEmpty()) route
        else route.substringBefore('/')

    object Welcome: Screen("welcome")
    object Age: Screen("age")
    object Gender: Screen("gender")
    object Height: Screen("height")
    object Weight: Screen("weight")
    object Goal: Screen("goal")
    object Activity: Screen("activity")
    object NutrientsGoal: Screen("nutrients_goal")
    object Summary: Screen("summary")

    object TrackerOverview: Screen("tracker_overview")
    object Search: Screen(
        route = "search"
                + "/{${NAV_ARG_MEAL_NAME}}"
                + "/{${NAV_ARG_DAY_OF_MONTH}}"
                + "/{${NAV_ARG_MONTH}}"
                + "/{${NAV_ARG_YEAR}}",
        navArgs = listOf(
            navArgument(NAV_ARG_MEAL_NAME) {
                type = NavType.StringType
            },
            navArgument(NAV_ARG_DAY_OF_MONTH) {
                type = NavType.IntType
            },
            navArgument(NAV_ARG_MONTH) {
                type = NavType.IntType
            },
            navArgument(NAV_ARG_YEAR) {
                type = NavType.IntType
            },
        )
    )

    companion object NavArgs {
        object Search {
            const val NAV_ARG_MEAL_NAME = "mealName"
            const val NAV_ARG_DAY_OF_MONTH = "dayOfMonth"
            const val NAV_ARG_MONTH = "month"
            const val NAV_ARG_YEAR = "year"
        }
    }
}

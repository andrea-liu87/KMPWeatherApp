package com.andreasgift.kmpweatherapp.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Destinations used in the [KMPWeatherApp].
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val SEE_MORE_ROUTE = "seeMore"
}

/**
 * Remembers and creates an instance of [WeatherNavController]
 */
@Composable
fun rememberWeatherNavController(navController: NavHostController = rememberNavController()): WeatherNavController =
    remember(navController) {
    WeatherNavController(navController)
}

/**
 * Responsible for holding UI Navigation logic.
 */
@Stable
class WeatherNavController(
    val navController: NavHostController,
) {

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    private val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToSeeMoreScreen(route: String){
        if (route != currentRoute) {
            navController.navigate(route)
            }
    }
}

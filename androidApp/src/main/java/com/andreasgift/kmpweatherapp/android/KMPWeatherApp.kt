package com.andreasgift.kmpweatherapp.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andreasgift.kmpweatherapp.WeatherAPI
import com.andreasgift.kmpweatherapp.android.ui.home.Home
import com.andreasgift.kmpweatherapp.android.ui.navigation.MainDestinations
import com.andreasgift.kmpweatherapp.android.ui.navigation.rememberWeatherNavController
import com.andreasgift.kmpweatherapp.android.ui.theme.WeatherTheme

@Composable
fun KMPWeatherApp() {
    WeatherTheme {
        val weatherNavController = rememberWeatherNavController()
        val api = WeatherAPI()
        NavHost(
            navController = weatherNavController.navController,
            startDestination = MainDestinations.HOME_ROUTE
        ) {
            weatherNavGraph(
                upPress = weatherNavController::upPress,
                onNavigateToRoute = weatherNavController::navigateToSeeMoreScreen,
                api = api
            )
        }
    }
}

private fun NavGraphBuilder.weatherNavGraph(
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
    api: WeatherAPI
) {
    composable(MainDestinations.HOME_ROUTE) {
        Home( onNavigateToRoute, modifier, api)
    }
    composable(MainDestinations.SEE_MORE_ROUTE) { from ->

    }
}
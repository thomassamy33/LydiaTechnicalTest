package com.tsamy.lydiatechnicaltest.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tsamy.lydiatechnicaltest.ui.screens.contactDetails.ContactDetailScreen
import com.tsamy.lydiatechnicaltest.ui.screens.home.HomeScreen
import com.tsamy.lydiatechnicaltest.ui.screens.splash.SplashScreen

@Composable
fun Application() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ApplicationScreen.Splash.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(ApplicationScreen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(ApplicationScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = ApplicationScreen.ContactDetail.route,
            arguments = listOf(
                navArgument(name = navigationInputArgument) {
                    type = NavType.IntType
                },
            )
        ) {
            ContactDetailScreen(navController = navController)
        }
    }
}
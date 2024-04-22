package com.tsamy.lydiatechnicaltest.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.tsamy.lydiatechnicaltest.ui.screens.ApplicationScreen
import com.tsamy.lydiatechnicaltest.ui.screens.splash.logic.SplashEvent
import com.tsamy.lydiatechnicaltest.ui.screens.splash.logic.SplashViewModel
import com.tsamy.lydiatechnicaltest.ui.screens.splash.views.Splash

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.event
            .collect { event ->
                when (event) {
                    SplashEvent.NavigateToHome -> {
                        val currentRoute = navController.currentDestination?.route
                        val navOptions = if (currentRoute != null) navOptions {
                            popUpTo(
                                route = currentRoute,
                                popUpToBuilder = { inclusive = true }
                            )
                        } else null
                        navController.navigate(ApplicationScreen.Home.route, navOptions)
                    }
                }
            }
    }

    Splash(onInteraction = viewModel::onInteraction)
}
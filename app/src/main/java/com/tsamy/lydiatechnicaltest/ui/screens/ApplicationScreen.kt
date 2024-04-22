package com.tsamy.lydiatechnicaltest.ui.screens

const val navigationInputArgument = "input"


sealed class ApplicationScreen(val route: String) {
    data object Splash : ApplicationScreen("splash")
    data object Home : ApplicationScreen("home")
    data object ContactDetail : ApplicationScreen("contact_detail/{$navigationInputArgument}")
}
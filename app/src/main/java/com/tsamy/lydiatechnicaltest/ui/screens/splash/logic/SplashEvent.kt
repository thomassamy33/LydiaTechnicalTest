package com.tsamy.lydiatechnicaltest.ui.screens.splash.logic

sealed class SplashEvent {
    data object NavigateToHome : SplashEvent()
}
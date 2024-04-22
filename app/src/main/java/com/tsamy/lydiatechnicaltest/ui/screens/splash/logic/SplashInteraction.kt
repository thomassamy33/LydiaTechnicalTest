package com.tsamy.lydiatechnicaltest.ui.screens.splash.logic

sealed class SplashInteraction {
    data object OnDelayEnded: SplashInteraction()
}
package com.tsamy.lydiatechnicaltest.ui.screens.home.logic

sealed class HomeInteraction {
    data class OnContactSelected(val id: Int) : HomeInteraction()
}
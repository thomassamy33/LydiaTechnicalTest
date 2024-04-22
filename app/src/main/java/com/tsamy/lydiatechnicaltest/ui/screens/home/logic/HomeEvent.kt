package com.tsamy.lydiatechnicaltest.ui.screens.home.logic

sealed class HomeEvent {
    data class NavigateToContactDetail(val id: Int) : HomeEvent()
}
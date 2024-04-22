package com.tsamy.lydiatechnicaltest.ui.screens.contactDetails.logic

sealed class ContactDetailEvent {
    data object NavigateBack : ContactDetailEvent()
    data class NavigateToMail(val email: String) : ContactDetailEvent()
    data class NavigateToPhone(val number: String) : ContactDetailEvent()
    data class NavigateToMap(val address: String) : ContactDetailEvent()
}
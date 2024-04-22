package com.tsamy.lydiatechnicaltest.ui.screens.contactDetails.logic

sealed class ContactDetailInteraction {
    data object OnBackPressed : ContactDetailInteraction()
    data object OnEmailPressed : ContactDetailInteraction()
    data object OnAddressPressed : ContactDetailInteraction()
    data class OnPhonePressed(val phone: String) : ContactDetailInteraction()
}
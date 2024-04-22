package com.tsamy.lydiatechnicaltest.ui.screens.contactDetails.logic

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsamy.lydiatechnicaltest.domain.entity.Contact
import com.tsamy.lydiatechnicaltest.domain.usecase.GetContactByIdUseCaseDefault
import com.tsamy.lydiatechnicaltest.ui.screens.navigationInputArgument
import com.tsamy.lydiatechnicaltest.utils.ChannelSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getContactById: GetContactByIdUseCaseDefault,
) : ViewModel() {

    private val contactId = savedStateHandle.get<Int>(navigationInputArgument) ?: 0

    val contactFlow: MutableStateFlow<Contact?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            val v = getContactById(contactId).first()
            contactFlow.emit(v)
        }
    }

    private val mutableEvent = ChannelSharedFlow<ContactDetailEvent>(viewModelScope)
    val event: SharedFlow<ContactDetailEvent> = mutableEvent

    fun onInteraction(interaction: ContactDetailInteraction) {
        when (interaction) {
            is ContactDetailInteraction.OnBackPressed -> onBackPressed()
            ContactDetailInteraction.OnEmailPressed -> onEmailPressed()
            is ContactDetailInteraction.OnPhonePressed -> onPhonePressed(interaction.phone)
            ContactDetailInteraction.OnAddressPressed -> onAddressPressed()
        }
    }

    private fun onAddressPressed() {
        viewModelScope.launch {
            val address = contactFlow.first()?.address ?: return@launch
            val addressString =
                "${address.number}+${address.street}+${address.postCode}+${address.city}"

            val event = ContactDetailEvent.NavigateToMap(addressString)
            mutableEvent.emit(event)
        }
    }

    private fun onPhonePressed(phone: String) {
        viewModelScope.launch {
            val event = ContactDetailEvent.NavigateToPhone(phone)
            mutableEvent.emit(event)
        }
    }

    private fun onEmailPressed() {
        viewModelScope.launch {
            val email = contactFlow.first()?.email ?: return@launch
            val event = ContactDetailEvent.NavigateToMail(email)
            mutableEvent.emit(event)
        }
    }

    private fun onBackPressed() {
        viewModelScope.launch {
            val event = ContactDetailEvent.NavigateBack
            mutableEvent.emit(event)
        }
    }
}
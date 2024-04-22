package com.tsamy.lydiatechnicaltest.ui.screens.home.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.tsamy.lydiatechnicaltest.domain.usecase.FetchContactsUseCaseDefault
import com.tsamy.lydiatechnicaltest.network.NetworkConnectivityService
import com.tsamy.lydiatechnicaltest.network.NetworkStatus
import com.tsamy.lydiatechnicaltest.ui.components.ContactItem
import com.tsamy.lydiatechnicaltest.utils.ChannelSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    fetchBis: FetchContactsUseCaseDefault,
    networkConnectivityService: NetworkConnectivityService
) : ViewModel() {

    val networkStatusFlow: StateFlow<NetworkStatus> =
        networkConnectivityService.networkStatus.stateIn(
            initialValue = NetworkStatus.Unknown,
            scope = viewModelScope,
            started = WhileSubscribed(5000)
        )

    val contactsFlow: Flow<PagingData<ContactItem.Model>> =
        fetchBis()
            .map {
                it.map { contact ->
                    ContactItem.Model(
                        id = contact.id,
                        imageUrl = contact.mediumImageUrl,
                        name = "${contact.firstname} ${contact.lastname}",
                        phone = contact.phone
                    )
                }
            }.cachedIn(viewModelScope)

    private val mutableEvent = ChannelSharedFlow<HomeEvent>(viewModelScope)
    val event: SharedFlow<HomeEvent> = mutableEvent

    fun onInteraction(interaction: HomeInteraction) {
        when (interaction) {
            is HomeInteraction.OnContactSelected -> onContactSelected(interaction.id)
        }
    }

    private fun onContactSelected(id: Int) {
        viewModelScope.launch {
            val event = HomeEvent.NavigateToContactDetail(id)
            mutableEvent.emit(event)
        }
    }
}
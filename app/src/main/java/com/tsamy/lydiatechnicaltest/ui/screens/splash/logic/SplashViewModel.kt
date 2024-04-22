package com.tsamy.lydiatechnicaltest.ui.screens.splash.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsamy.lydiatechnicaltest.utils.ChannelSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val mutableEvent = ChannelSharedFlow<SplashEvent>(viewModelScope)
    val event: SharedFlow<SplashEvent> = mutableEvent

    fun onInteraction(interaction: SplashInteraction) {
        when (interaction) {
            SplashInteraction.OnDelayEnded -> onDelayEnded()
        }
    }

    private fun onDelayEnded() {
        viewModelScope.launch {
            mutableEvent.emit(SplashEvent.NavigateToHome)
        }
    }

}
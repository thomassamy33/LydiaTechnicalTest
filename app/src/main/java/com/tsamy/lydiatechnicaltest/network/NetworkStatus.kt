package com.tsamy.lydiatechnicaltest.network

sealed class NetworkStatus {
    data object Unknown : NetworkStatus()
    data object Connected : NetworkStatus()
    data object Disconnected : NetworkStatus()
}
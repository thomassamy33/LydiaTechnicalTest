package com.tsamy.lydiatechnicaltest.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
    context: Context,
) : Interceptor {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private fun isConnected(): Boolean {
        val network = connectivityManager.activeNetwork
        return network != null
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        if (isConnected()) {
            return chain.proceed(request)
        } else {
            throw NoNetworkException("Network Error")
        }
    }
}
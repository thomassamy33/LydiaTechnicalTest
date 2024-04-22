package com.tsamy.lydiatechnicaltest.di

import com.tsamy.lydiatechnicaltest.data.ContactRepositoryDefault
import com.tsamy.lydiatechnicaltest.domain.repository.ContactRepository
import com.tsamy.lydiatechnicaltest.network.NetworkConnectivityService
import com.tsamy.lydiatechnicaltest.network.NetworkConnectivityServiceDefault
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindContactsRepository(
        contactsRepositoryDefault: ContactRepositoryDefault
    ): ContactRepository

    @Binds
    @Singleton
    abstract fun bindNetworkConnectivityService(
        networkConnectivityServiceDefault: NetworkConnectivityServiceDefault
    ): NetworkConnectivityService

}
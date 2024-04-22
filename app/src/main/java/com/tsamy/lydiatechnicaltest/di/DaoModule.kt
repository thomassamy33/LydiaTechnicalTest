package com.tsamy.lydiatechnicaltest.di

import com.tsamy.lydiatechnicaltest.data.local.dao.ContactDao
import com.tsamy.lydiatechnicaltest.data.local.dao.RemoteKeyDao
import com.tsamy.lydiatechnicaltest.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideContactDao(appDatabase: AppDatabase): ContactDao = appDatabase.contactDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeyDao = appDatabase.remoteKeyDao()

}
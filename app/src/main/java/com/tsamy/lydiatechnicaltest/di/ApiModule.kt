package com.tsamy.lydiatechnicaltest.di

import com.tsamy.lydiatechnicaltest.data.remote.api.ContactsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideContactsApiService(retrofit: Retrofit): ContactsApiService {
        return retrofit.create(ContactsApiService::class.java)
    }
}
package com.tsamy.lydiatechnicaltest.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tsamy.lydiatechnicaltest.data.local.AppDatabase
import com.tsamy.lydiatechnicaltest.network.NetworkConnectivityServiceDefault
import com.tsamy.lydiatechnicaltest.network.NetworkInterceptor
import com.tsamy.lydiatechnicaltest.utils.UrlConfig.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val RETROFIT_CONNECT_READ_TIMEOUT = 30L

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNetworkConnectivityService(@ApplicationContext appContext: Context) =
        NetworkConnectivityServiceDefault(appContext)

    @Singleton
    @Provides
    fun provideNetworkInterceptor(@ApplicationContext appContext: Context) =
        NetworkInterceptor(appContext)

    @Provides
    @Singleton
    fun provideOkHttpClient(networkInterceptor: NetworkInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .readTimeout(RETROFIT_CONNECT_READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(RETROFIT_CONNECT_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RETROFIT_CONNECT_READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "room_database"
        ).build()
}
package com.tsamy.lydiatechnicaltest.data.remote.api

import com.tsamy.lydiatechnicaltest.data.remote.model.ContactsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactsApiService {

    @GET("api/1.3/")
    suspend fun getContacts(
        @Query("seed") seed: String,
        @Query("results") results: Int? = 20,
        @Query("page") page: Int,
    ): Response<ContactsDto>

}
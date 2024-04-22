package com.tsamy.lydiatechnicaltest.domain.repository

import androidx.paging.PagingData
import com.tsamy.lydiatechnicaltest.data.local.model.ContactDb
import com.tsamy.lydiatechnicaltest.domain.entity.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    /**
     * Get contact by Id
     */
    suspend fun getById(id: Int): Flow<Contact>

    /**
     * Fetch contacts with pagination
     */
    fun fetch(): Flow<PagingData<ContactDb>>

}
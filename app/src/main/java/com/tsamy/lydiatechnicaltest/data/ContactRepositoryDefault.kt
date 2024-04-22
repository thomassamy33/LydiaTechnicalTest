package com.tsamy.lydiatechnicaltest.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tsamy.lydiatechnicaltest.data.local.AppDatabase
import com.tsamy.lydiatechnicaltest.data.local.model.ContactDb
import com.tsamy.lydiatechnicaltest.data.remote.api.ContactsApiService
import com.tsamy.lydiatechnicaltest.data.remote.mapper.toEntity
import com.tsamy.lydiatechnicaltest.data.remote.mediator.ContactsRemoteMediator
import com.tsamy.lydiatechnicaltest.data.remote.model.PaginationConfig.PAGE_SIZE
import com.tsamy.lydiatechnicaltest.domain.entity.Contact
import com.tsamy.lydiatechnicaltest.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * Default implementation of [ContactRepository].
 */
class ContactRepositoryDefault @Inject constructor(
    private val api: ContactsApiService,
    private val appDatabase: AppDatabase
) : ContactRepository {

    override suspend fun getById(id: Int): Flow<Contact> =
        flowOf(appDatabase.contactDao().getContactById(id).toEntity())


    @OptIn(ExperimentalPagingApi::class)
    override fun fetch(): Flow<PagingData<ContactDb>> {
        val dbSource = {
            appDatabase.contactDao().getAll()
        }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            ),
            remoteMediator = ContactsRemoteMediator(api, appDatabase),
            pagingSourceFactory = dbSource
        ).flow
    }
}
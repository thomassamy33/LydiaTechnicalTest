package com.tsamy.lydiatechnicaltest.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.tsamy.lydiatechnicaltest.data.local.AppDatabase
import com.tsamy.lydiatechnicaltest.data.local.model.ContactDb
import com.tsamy.lydiatechnicaltest.data.local.model.RemoteKeyDb
import com.tsamy.lydiatechnicaltest.data.remote.api.ContactsApiService
import com.tsamy.lydiatechnicaltest.data.remote.mapper.toDb
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class ContactsRemoteMediator(
    private val api: ContactsApiService,
    private val database: AppDatabase,
) : RemoteMediator<Int, ContactDb>() {

    private val remoteKeyDao = database.remoteKeyDao()
    private val contactDao = database.contactDao()

    override suspend fun initialize(): InitializeAction {
        val remoteKey = database.withTransaction {
            remoteKeyDao.getKeyByContact("contact")
        } ?: return InitializeAction.LAUNCH_INITIAL_REFRESH

        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)

        return if ((System.currentTimeMillis() - remoteKey.lastUpdated) >= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ContactDb>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }

                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.getKeyByContact("contact")
                    } ?: return MediatorResult.Success(true)

                    if (remoteKey.nextPage == null) {
                        return MediatorResult.Success(true)
                    }

                    remoteKey.nextPage
                }
            }

            val result = api.getContacts(
                seed = "lydia",
                results = 20,
                page = page
            ).body()?.results.orEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    contactDao.deleteAll()
                }

                val nextPage = if (result.isEmpty()) null else page + 1

                val contactsDb = result.map {
                    it.toDb()
                }

                remoteKeyDao.insertKey(
                    RemoteKeyDb(
                        id = "contact",
                        nextPage = nextPage,
                        lastUpdated = System.currentTimeMillis()
                    )
                )
                contactDao.insertAll(contactsDb)
            }

            MediatorResult.Success(endOfPaginationReached = result.isEmpty())
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }
}
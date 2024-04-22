package com.tsamy.lydiatechnicaltest.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.tsamy.lydiatechnicaltest.domain.repository.ContactRepository
import com.tsamy.lydiatechnicaltest.data.remote.mapper.toEntity
import com.tsamy.lydiatechnicaltest.domain.entity.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface FetchContactsUseCase {

    /**
     * Fetch contacts.
     */
    operator fun invoke(): Flow<PagingData<Contact>>

}

/**
 * Default implementation of [FetchContactsUseCase] using the
 * contact repository.
 */
class FetchContactsUseCaseDefault @Inject constructor(
    private val contactRepository: ContactRepository
) : FetchContactsUseCase {

    override operator fun invoke(): Flow<PagingData<Contact>> =
        contactRepository.fetch().map { pagingData ->
            pagingData.map { it.toEntity() }
        }
}
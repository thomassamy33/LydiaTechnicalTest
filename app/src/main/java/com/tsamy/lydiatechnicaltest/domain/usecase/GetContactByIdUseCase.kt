package com.tsamy.lydiatechnicaltest.domain.usecase

import com.tsamy.lydiatechnicaltest.domain.entity.Contact
import com.tsamy.lydiatechnicaltest.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetContactByIdUseCase {

    /**
     * Get contact by id.
     */
    suspend operator fun invoke(id: Int): Flow<Contact>

}

/**
 * Default implementation of [GetContactByIdUseCase] using the
 * contact repository.
 */
class GetContactByIdUseCaseDefault @Inject constructor(
    private val contactRepository: ContactRepository
) : GetContactByIdUseCase {

    override suspend operator fun invoke(id: Int): Flow<Contact> =
        contactRepository.getById(id)
}
package com.tsamy.lydiatechnicaltest.domain.usecase

import com.tsamy.lydiatechnicaltest.domain.repository.ContactRepository
import com.tsamy.lydiatechnicaltest.domain.entity.Address
import com.tsamy.lydiatechnicaltest.domain.entity.Contact
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetContactByIdUseCaseTest {

    @MockK
    lateinit var contactRepository: ContactRepository

    @InjectMockKs
    lateinit var getContactByIdUseCase: GetContactByIdUseCaseDefault

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `get contact by id success`() = runTest {
        //GIVEN
        val contactId = 1
        val contact = Contact(
            contactId,
            "thomas@samy.fr",
            "Thomas",
            "SAMY",
            "1999-11-11T12:12:12.500Z",
            Address(
                10,
                "rue du stade",
                "Paris",
                "France",
                "0.0",
                "0.0",
                "75016"
            ),
            "0501020304",
            "0601020304",
            "",
            ""
        )
        coEvery { contactRepository.getById(any()) } returns flowOf(contact)

        // WHEN
        val result = getContactByIdUseCase(contactId)

        // THEN
        TestCase.assertEquals(result.first(), contact)
        coVerify { contactRepository.getById(contactId) }
    }
}
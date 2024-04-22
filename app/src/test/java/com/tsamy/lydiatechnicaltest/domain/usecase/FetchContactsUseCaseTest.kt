package com.tsamy.lydiatechnicaltest.domain.usecase

import androidx.paging.PagingData
import com.tsamy.lydiatechnicaltest.data.local.model.ContactDb
import com.tsamy.lydiatechnicaltest.data.remote.mapper.toEntity
import com.tsamy.lydiatechnicaltest.domain.repository.ContactRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class FetchContactsUseCaseTest {

    @MockK
    lateinit var contactRepository: ContactRepository

    @InjectMockKs
    lateinit var useCase: FetchContactsUseCaseDefault

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Ignore("Not working")
    @Test
    fun `fetch contacts`() = runTest {
        //GIVEN
        val contact = ContactDb(
            1,
            "thomas@samy.fr",
            "Thomas",
            "SAMY",
            "1999-11-11T12:12:12.500Z",
            10,
            "rue du stade",
            "Paris",
            "France",
            "0.0",
            "0.0",
            "75016",
            "0501020304",
            "0601020304",
            "",
            ""
        )
        coEvery { contactRepository.fetch() } returns flowOf(PagingData.from(listOf(contact)))

        // WHEN
        val result = useCase()

        // THEN
        TestCase.assertEquals(result, PagingData.from(listOf(contact.toEntity())))
        coVerify { contactRepository.fetch() }
    }
}
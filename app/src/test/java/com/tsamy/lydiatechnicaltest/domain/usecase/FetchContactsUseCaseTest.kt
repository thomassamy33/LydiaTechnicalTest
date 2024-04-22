package com.tsamy.lydiatechnicaltest.domain.usecase

import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import com.tsamy.lydiatechnicaltest.data.local.model.ContactDb
import com.tsamy.lydiatechnicaltest.data.remote.mapper.toEntity
import com.tsamy.lydiatechnicaltest.domain.repository.ContactRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FetchContactsUseCaseTest {

    @MockK
    lateinit var contactRepository: ContactRepository

    @InjectMockKs
    lateinit var useCase: FetchContactsUseCaseDefault

    private lateinit var dispatcher: TestDispatcher

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dispatcher = StandardTestDispatcher()
    }

    @Test
    fun `fetch contacts`() = runTest(dispatcher) {
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
        val result = useCase().first().collectDataForTest()

        // THEN
        TestCase.assertEquals(result, listOf(contact.toEntity()))
        coVerify { contactRepository.fetch() }
    }

    private suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
        val dcb = object : DifferCallback {
            override fun onChanged(position: Int, count: Int) {}
            override fun onInserted(position: Int, count: Int) {}
            override fun onRemoved(position: Int, count: Int) {}
        }
        val items = mutableListOf<T>()
        val dif = object : PagingDataDiffer<T>(dcb, dispatcher) {
            override suspend fun presentNewList(
                previousList: NullPaddedList<T>,
                newList: NullPaddedList<T>,
                lastAccessedIndex: Int,
                onListPresentable: () -> Unit
            ): Int? {
                for (idx in 0 until newList.size)
                    items.add(newList.getFromStorage(idx))
                onListPresentable()
                return null
            }
        }
        dif.collectFrom(this)
        return items
    }
}
package com.ironflowers.fbt2.overview.ui.vm

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ironflowers.fbt2.core.domain.content.ContentItem
import com.ironflowers.fbt2.core.domain.content.ContentUseCases
import com.ironflowers.fbt2.test.ui.base.TestDispatchersProvider
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class OverviewViewModelTest {

    /**
     * In this test, LiveData will immediately post values without switching threads.
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<OverviewViewState>

    val contentItem = ContentItem("123", "title", "description", "https://ironflowers.com/image.jpg")
    val contentItems = listOf(contentItem)

    private val successContentUseCases = object : ContentUseCases {
        override suspend fun getAll(): List<ContentItem> = contentItems
        override suspend fun get(id: String): ContentItem = contentItem
    }
    val error = NetworkErrorException("Test exception")
    private val errorContentUseCases = object : ContentUseCases {
        override suspend fun getAll(): List<ContentItem> = throw error
        override suspend fun get(id: String): ContentItem = throw error
    }

    lateinit var subject: OverviewViewModel
    lateinit var errorSubject: OverviewViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        subject = OverviewViewModel(successContentUseCases, TestDispatchersProvider())
        errorSubject = OverviewViewModel(errorContentUseCases, TestDispatchersProvider())
    }

    @Test
    fun `loadItems shows loading viewState`() {
        subject.viewState.observeForever(observer)
        subject.loadItems()
        verify(observer).onChanged(OverviewViewState.Loading)
    }

    @Test
    fun `loadItems success shows success ViewState`() {
        subject.viewState.observeForever(observer)
        subject.loadItems()
        val expected = OverviewViewState.Success(contentItems)
        verify(observer).onChanged(expected)
        assertEquals(subject.viewState.value, expected)
    }

    @Test
    fun `loadItems error shows error`() {
        errorSubject.viewState.observeForever(observer)
        errorSubject.loadItems()
        val expected = OverviewViewState.Error(error)
        verify(observer).onChanged(expected)
        assertEquals(errorSubject.viewState.value, expected)
    }
}
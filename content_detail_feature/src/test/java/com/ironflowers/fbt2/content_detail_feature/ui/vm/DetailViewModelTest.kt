package com.ironflowers.fbt2.content_detail_feature.ui.vm

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ironflowers.fbt2.core.domain.content.ContentItem
import com.ironflowers.fbt2.core.domain.content.ContentUseCases
import com.ironflowers.fbt2.test.ui.base.TestDispatchersProvider
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
class DetailViewModelTest() {

    /**
     * In this test, LiveData will immediately post values without switching threads.
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<DetailViewState>

    val contentItem = ContentItem("123", "title", "description", "https://ironflowers.com/image.jpg")

    private val successContentUseCases = object : ContentUseCases {
        override suspend fun getAll(): List<ContentItem> = listOf()
        override suspend fun get(id: String): ContentItem = contentItem
    }
    private val errorContentUseCases = object : ContentUseCases {
        override suspend fun getAll(): List<ContentItem> = throw NetworkErrorException("Test exception")
        override suspend fun get(id: String): ContentItem = throw NetworkErrorException("Test exception")
    }

    lateinit var subject: DetailViewModel
    lateinit var errorSubject: DetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        subject = DetailViewModel(successContentUseCases, contentItem.id, TestDispatchersProvider())
        errorSubject = DetailViewModel(errorContentUseCases, contentItem.id, TestDispatchersProvider())
    }

    @Test
    fun `loadItem shows loading`() {
        subject.viewState.observeForever(observer)
        subject.loadContent()
        verify(observer).onChanged(DetailViewState(null, true, false))
    }

    @Test
    fun `loadItem success shows item`() {
        subject.viewState.observeForever(observer)
        subject.loadContent()
        val expected = DetailViewState(contentItem, false, false)
        verify(observer).onChanged(expected)
    }

    @Test
    fun `loadItem error shows only error`() {
        errorSubject.viewState.observeForever(observer)
        errorSubject.loadContent()
        verify(observer).onChanged(DetailViewState(null, false, true))
    }
}


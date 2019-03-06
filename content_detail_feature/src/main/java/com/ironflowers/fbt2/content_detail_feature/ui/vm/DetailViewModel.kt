package com.ironflowers.fbt2.content_detail_feature.ui.vm

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ironflowers.fbt2.core.domain.content.ContentUseCases
import com.ironflowers.fbt2.core.ui.common.DispatchersProvider
import kotlinx.coroutines.*

class DetailViewModel(
    private val contentUseCases: ContentUseCases, private val contentId: String,
    dispatchersProvider: DispatchersProvider
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(dispatchersProvider.Main + viewModelJob)

    val viewState = MutableLiveData<DetailViewState>()

    fun setup() = loadContent()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    @VisibleForTesting
    fun loadContent() {

        viewState.value = DetailViewState(showLoadingIndicator = true)

        uiScope.launch {
            val viewState = try {
                    DetailViewState(contentItem = contentUseCases.get(contentId))
                } catch (e: Exception) {
                    DetailViewState(showErrorMessage = true)
                }
            this@DetailViewModel.viewState.value = viewState
        }
    }
}
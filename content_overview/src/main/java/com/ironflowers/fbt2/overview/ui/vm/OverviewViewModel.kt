package com.ironflowers.fbt2.overview.ui.vm

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ironflowers.fbt2.core.domain.content.ContentUseCases
import com.ironflowers.fbt2.core.ui.common.DispatchersProvider
import com.ironflowers.fbt2.core.ui.common.SingleLiveEvent
import com.ironflowers.fbt2.overview.ui.OverViewEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OverviewViewModel(
    private val contentUseCases: ContentUseCases,
    dispatchersProvider: DispatchersProvider
) : ViewModel(),
    OverViewEventListener {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(dispatchersProvider.Main + viewModelJob)

    val viewState = MutableLiveData<OverviewViewState>()
    val viewEvents = SingleLiveEvent<OverviewViewEvent>()

    fun setup() = loadItems()

    override fun onOverviewItemClicked(contentId: String) {
        viewEvents.value = OverviewViewEvent.NavigateToContentDetails(contentId)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    @VisibleForTesting
    fun loadItems() {

        viewState.value = OverviewViewState.Loading

        uiScope.launch {
            val viewState = try {
                OverviewViewState.Success(contentUseCases.getAll())
            } catch (e: Exception) {
                OverviewViewState.Error(e)
            }
            this@OverviewViewModel.viewState.value = viewState
        }
    }
}
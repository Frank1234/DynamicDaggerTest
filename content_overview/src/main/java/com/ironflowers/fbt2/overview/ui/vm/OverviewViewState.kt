package com.ironflowers.fbt2.overview.ui.vm

import com.ironflowers.fbt2.core.domain.content.ContentItem

sealed class OverviewViewState {

    data class Success(val items: List<ContentItem>) : OverviewViewState()

    data class Error(val exception: Throwable) : OverviewViewState()

    object Loading : OverviewViewState()

    fun showError() = this is Error

    fun showList() = this is Success

    fun showLoading() = this is Loading
}
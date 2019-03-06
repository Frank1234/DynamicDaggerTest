package com.ironflowers.fbt2.content_detail_feature.ui.vm

import com.ironflowers.fbt2.core.domain.content.ContentItem

data class DetailViewState(
    val contentItem: ContentItem? = null,
    val showLoadingIndicator: Boolean = false,
    val showErrorMessage: Boolean = false
)
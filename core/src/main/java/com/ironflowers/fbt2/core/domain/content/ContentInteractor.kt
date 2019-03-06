package com.ironflowers.fbt2.core.domain.content

import com.ironflowers.fbt2.core.data.remote.content.ContentRepo
import com.ironflowers.fbt2.core.data.remote.content.RemoteContentItem
import com.ironflowers.fbt2.core.ui.common.DispatchersProvider
import kotlinx.coroutines.withContext

class ContentInteractor(
    private val remoteContentRepo: ContentRepo,
    private val dispatchersProvider: DispatchersProvider
) : ContentUseCases {

    override suspend fun getAll(): List<ContentItem> = withContext(dispatchersProvider.IO) {
        remoteContentRepo.getAll().map { parseToContentItem(it) }
    }

    override suspend fun get(id: String) = withContext(dispatchersProvider.IO) {
        parseToContentItem(remoteContentRepo.get(id))
    }

    private fun parseToContentItem(remoteContentItem: RemoteContentItem) = ContentItem(
        id = remoteContentItem.id,
        title = remoteContentItem.title,
        description = remoteContentItem.description,
        imageUrl = remoteContentItem.imageUrl
    )
}
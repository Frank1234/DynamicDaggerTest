package com.ironflowers.fbt2.overview.ui

import com.airbnb.epoxy.Typed2EpoxyController
import com.ironflowers.fbt2.core.domain.content.ContentItem
import com.ironflowers.fbt2.ui.OverviewListItemBindingModel_

/**
 * Controller that binds [OverviewItemViewState]s to the epoxy classes that populate the RecyclerView.
 */
class OverviewItemController(val eventListener: OverViewEventListener) :
    Typed2EpoxyController<List<ContentItem>, Boolean>() {

    override fun buildModels(items: List<ContentItem>, loadingMore: Boolean) {
        for (item in items) {
            OverviewListItemBindingModel_()
                .id(item.id)
                .item(item)
                .eventListener(eventListener)
                .addTo(this)
        }
    }
}
package com.ironflowers.fbt2.core.data.remote.content

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName
import com.ironflowers.fbt2.core.ui.common.extensions.HasId

/**
 * @property imageUrl Url of the image on the storage, relative to the storage content root.
 */
data class RemoteContentItem(
    @get:Exclude override var id: String = "",
    @PropertyName(KEY_TITLE) val title: String = "",
    @PropertyName(DESCRIPTION) val description: String = "",
    @PropertyName(IMAGE_URL) val imageUrl: String? = null
) : HasId {

    companion object {
        const val KEY_TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGE_URL = "imageUrl"
    }
}
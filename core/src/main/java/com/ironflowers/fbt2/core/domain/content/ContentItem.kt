package com.ironflowers.fbt2.core.domain.content

/**
 * @property imageUrl Url of the image on the storage, relative to the storage content root.
 */
data class ContentItem(val id: String, val title: String, val description: String, val imageUrl: String? = null)
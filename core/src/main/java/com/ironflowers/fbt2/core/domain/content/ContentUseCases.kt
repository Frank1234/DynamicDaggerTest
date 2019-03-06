package com.ironflowers.fbt2.core.domain.content

interface ContentUseCases {

    suspend fun getAll(): List<ContentItem>

    suspend fun get(id: String): ContentItem
}
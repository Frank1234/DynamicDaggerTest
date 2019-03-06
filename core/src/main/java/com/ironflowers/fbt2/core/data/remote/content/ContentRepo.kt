package com.ironflowers.fbt2.core.data.remote.content

interface ContentRepo {

    suspend fun getAll(): List<RemoteContentItem>

    suspend fun get(id: String): RemoteContentItem
}
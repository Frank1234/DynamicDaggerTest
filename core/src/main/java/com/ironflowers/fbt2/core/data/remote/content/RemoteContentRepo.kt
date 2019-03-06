package com.ironflowers.fbt2.core.data.remote.content

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.ironflowers.fbt2.core.ui.common.extensions.toObjectWithId
import com.ironflowers.fbt2.core.ui.common.extensions.toObjectsWithId
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RemoteContentRepo(private val db: FirebaseFirestore) : ContentRepo {

    companion object {
        const val COLLECTION_CONTENT = "content"
    }

    override suspend fun getAll(): List<RemoteContentItem> = suspendCoroutine { cont ->
        db.collection(COLLECTION_CONTENT)
            .orderBy(RemoteContentItem.KEY_TITLE, Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                cont.resume(mapToRemoteContentItems(result))
            }
            .addOnFailureListener { exception ->
                Timber.w(exception, "Error getting content from db.")
                cont.resumeWithException(exception)
            }
    }

    override suspend fun get(id: String): RemoteContentItem = suspendCoroutine { cont ->
        db.collection("content")
            .document(id)
            .get()
            .addOnSuccessListener { result ->
                cont.resume(mapToRemoteContentItem(result))
            }
            .addOnFailureListener { exception ->
                Timber.w(exception, "Error getting content from db.")
                cont.resumeWithException(exception)
            }
    }

    private fun mapToRemoteContentItem(dbItem: DocumentSnapshot): RemoteContentItem = dbItem.toObjectWithId()

    private fun mapToRemoteContentItems(dbResult: QuerySnapshot) = dbResult.toObjectsWithId<RemoteContentItem>()
}
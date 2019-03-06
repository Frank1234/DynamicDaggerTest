package com.ironflowers.fbt2.core.ui.common.extensions

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface HasId {
    var id: String
}

inline fun <reified T : HasId> DocumentSnapshot.toObjectWithId(): T {
    return this.toObject(T::class.java)!!.also {
        it.id = this.id
    }
}

inline fun <reified T : HasId> QuerySnapshot.toObjectsWithId(): List<T> {
    return this.documents.map {
        it.toObjectWithId<T>()
    }
}
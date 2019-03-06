package com.ironflowers.fbt2.core.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.ironflowers.fbt2.core.data.remote.content.RemoteContentRepo
import com.ironflowers.fbt2.core.domain.content.ContentUseCases
import com.ironflowers.fbt2.core.ui.common.DispatchersProvider
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 */
@Singleton
@Component(modules = [CoreModule::class, ContextModule::class])
interface CoreComponent {

    /*
     * The methods below are sort of a 'promise' that this Component can provide these objects to dependent Components.
     * This is done because we cannot use the sub-components (hard coded connection) with our (dynamic) feature modules.
     */

    fun context(): Context
    fun firebaseFirestore(): FirebaseFirestore
    fun remoteContentRepo(): RemoteContentRepo
    fun contentUseCases(): ContentUseCases
    fun provideDispatchersProvider(): DispatchersProvider
}
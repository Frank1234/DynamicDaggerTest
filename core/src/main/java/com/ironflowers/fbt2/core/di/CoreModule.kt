package com.ironflowers.fbt2.core.di

import com.google.firebase.firestore.FirebaseFirestore
import com.ironflowers.fbt2.core.data.remote.content.RemoteContentRepo
import com.ironflowers.fbt2.core.domain.content.ContentInteractor
import com.ironflowers.fbt2.core.domain.content.ContentUseCases
import com.ironflowers.fbt2.core.ui.common.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class CoreModule {

    @Reusable
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Reusable
    @Provides
    fun provideRemoteContentRepo(firebaseFirestore: FirebaseFirestore): RemoteContentRepo =
        RemoteContentRepo(firebaseFirestore)

    @Reusable
    @Provides
    fun provideContentUseCases(
        contentRepo: RemoteContentRepo,
        dispatchersProvider: DispatchersProvider
    ): ContentUseCases =
        ContentInteractor(contentRepo, dispatchersProvider)

    @Reusable
    @Provides
    fun provideDispatchersProvider() = DispatchersProvider()
}
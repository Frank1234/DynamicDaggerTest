package com.ironflowers.fbt2.ui.main.di

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class MainActivityModule {

    @Provides
    @Reusable
    fun splitInstallManager(context: Context): SplitInstallManager = SplitInstallManagerFactory.create(context)
}
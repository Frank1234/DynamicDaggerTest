package com.ironflowers.fbt2.ui.main

import android.app.Application
import com.ironflowers.fbt2.BuildConfig
import com.ironflowers.fbt2.core.di.ContextModule
import com.ironflowers.fbt2.core.di.CoreComponent
import com.ironflowers.fbt2.core.di.CoreComponentProvider
import com.ironflowers.fbt2.core.di.DaggerCoreComponent
import timber.log.Timber
import javax.inject.Singleton

class MainApplication : Application(), CoreComponentProvider {

    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()

        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()

        initLogging()
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun provideCoreComponent(): CoreComponent = coreComponent
}
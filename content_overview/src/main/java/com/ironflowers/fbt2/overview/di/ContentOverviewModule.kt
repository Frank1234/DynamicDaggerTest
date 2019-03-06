package com.ironflowers.fbt2.overview.di

import com.ironflowers.fbt2.overview.ui.di.OverviewFragmentModule
import dagger.Module

@Module(includes = [OverviewFragmentModule::class]) // connected (sub)modules
class ContentOverviewModule {

    // still empty, we might use this in the future for everything not view-related.
}

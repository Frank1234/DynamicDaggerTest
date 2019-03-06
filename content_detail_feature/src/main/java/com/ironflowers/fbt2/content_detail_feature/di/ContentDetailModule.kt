package com.ironflowers.fbt2.content_detail_feature.di

import com.ironflowers.fbt2.content_detail_feature.ui.di.DetailFragmentModule
import dagger.Module

@Module(includes = [DetailFragmentModule::class]) // connected (sub)modules
class ContentDetailModule {

    // still empty, we might use this in the future for everything not view-related.
}
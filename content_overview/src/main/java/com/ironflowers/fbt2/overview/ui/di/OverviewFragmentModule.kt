package com.ironflowers.fbt2.overview.ui.di

import com.ironflowers.fbt2.core.domain.content.ContentUseCases
import com.ironflowers.fbt2.core.ui.common.DispatchersProvider
import com.ironflowers.fbt2.core.ui.common.extensions.getViewModel
import com.ironflowers.fbt2.overview.ui.OverviewFragment
import com.ironflowers.fbt2.overview.ui.vm.OverviewViewModel
import dagger.Module
import dagger.Provides

@Module
class OverviewFragmentModule(val fragment: OverviewFragment) {

    @Provides
    fun overviewViewModel(
        contentUseCases: ContentUseCases,
        dispatchersProvider: DispatchersProvider
    ): OverviewViewModel =
        fragment.getViewModel {
            OverviewViewModel(contentUseCases, dispatchersProvider)
        }
}
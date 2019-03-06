package com.ironflowers.fbt2.overview.di

import com.ironflowers.fbt2.core.di.CoreComponent
import com.ironflowers.fbt2.core.di.scope.FragmentScope
import com.ironflowers.fbt2.overview.ui.OverviewFragment
import dagger.Component

@FragmentScope // add scope, because we cannot use the same scope as the component we depend upon (CoreComponent).
@Component(
    modules = [ContentOverviewModule::class], // this feature's main/root module, it might contain submodules.
    dependencies = [CoreComponent::class] // so we can use all objects that CoreComponent provides
)
interface ContentOverviewComponent {

    fun inject(overviewFragment: OverviewFragment)
}
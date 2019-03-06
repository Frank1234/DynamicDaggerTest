package com.ironflowers.fbt2.ui.main.di

import com.ironflowers.fbt2.core.di.CoreComponent
import com.ironflowers.fbt2.core.di.scope.ActivityScope
import com.ironflowers.fbt2.ui.main.MainActivity
import dagger.Component

@ActivityScope // add scope, because we cannot use the same scope as the component we depend upon (CoreComponent).
@Component(
    modules = [MainActivityModule::class], // this feature's main/root module, it might contain submodules.
    dependencies = [CoreComponent::class] // so we can use all objects that CoreComponent provides
)
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}
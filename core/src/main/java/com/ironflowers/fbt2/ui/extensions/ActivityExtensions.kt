package com.ironflowers.fbt2.ui.extensions

import android.app.Activity
import com.ironflowers.fbt2.core.di.CoreComponentProvider

fun Activity.coreComponent() =
    (applicationContext as? CoreComponentProvider)?.provideCoreComponent()
        ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")


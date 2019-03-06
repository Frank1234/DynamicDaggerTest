package com.ironflowers.fbt2.core.ui.common

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class DispatchersProvider() {
    open val Main: CoroutineContext by lazy { Dispatchers.Main }
    open val IO: CoroutineContext by lazy { Dispatchers.IO }
}
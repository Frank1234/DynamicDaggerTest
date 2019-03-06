package com.ironflowers.fbt2.ui.base

import androidx.fragment.app.Fragment
import com.ironflowers.fbt2.ui.extensions.coreComponent

fun Fragment.coreComponent() = requireActivity().coreComponent()

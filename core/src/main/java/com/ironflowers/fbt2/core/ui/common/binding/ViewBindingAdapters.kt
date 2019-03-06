package com.ironflowers.fbt2.core.ui.common.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.ironflowers.fbt2.core.ui.common.extensions.setVisible
import com.ironflowers.fbt2.core.ui.common.extensions.setVisibleOrGone

/**
 * Binding adapters for custom data binding attributes.
 */

@BindingAdapter("glideSrc")
fun setImageViewResource(imageView: ImageView, publicImageUrl: String?) {

    publicImageUrl?.let {
        Glide.with(imageView.context)
            .load(publicImageUrl)
            .transition(withCrossFade())
            .into(imageView)
    }
}

@BindingAdapter("visibleOrGone")
fun setVisibleOrGone(view: View, show: Boolean) = view.setVisibleOrGone(show)

@BindingAdapter("visible")
fun setVisible(view: View, show: Boolean) = view.setVisible(show)
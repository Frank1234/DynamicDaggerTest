package com.ironflowers.fbt2.overview.ui.vm

sealed class OverviewViewEvent {
    class NavigateToContentDetails(val contentId: String) : OverviewViewEvent()
}
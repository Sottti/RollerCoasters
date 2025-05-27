package com.sottti.roller.coasters.presentation.about.me.model

internal data class AboutMeBottomSheetPreviewState(
    val onAction: (AboutMeAction) -> Unit,
    val state: TopicDescription,
)
package com.sottti.roller.coasters.presentation.about.me.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class AboutMeBottomSheetPreviewState(
    val onAction: (AboutMeAction) -> Unit,
    val state: TopicDescription,
)
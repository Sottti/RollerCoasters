package com.sottti.roller.coasters.presentation.about.me.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction

internal const val URL_RES_ID = 12345
private val primaryColor = Color.Magenta
internal val toolbarColorInt = primaryColor.toArgb()
internal val openUrlAction = AboutMeAction.OpenUrl(
    urlResId = URL_RES_ID,
    primaryColor = primaryColor,
)

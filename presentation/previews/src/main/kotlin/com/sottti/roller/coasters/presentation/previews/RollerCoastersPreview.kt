package com.sottti.roller.coasters.presentation.previews

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Preview(
    device = device,
    group = lightThemeGroup,
    name = "English Light",
)
@Preview(
    device = device,
    group = darkThemeGroup,
    name = "English Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
@Preview(
    device = device,
    group = lightThemeGroup,
    locale = "es",
    name = "Spanish Light",
)
@Preview(
    device = device,
    group = darkThemeGroup,
    locale = "es",
    name = "Spanish Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
@Preview(
    device = device,
    group = lightThemeGroup,
    locale = "gl",
    name = "Galician Light",
)
@Preview(
    device = device,
    group = darkThemeGroup,
    locale = "gl",
    name = "Galician Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
public annotation class RollerCoastersPreview
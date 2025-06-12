package com.sottti.roller.coasters.presentation.previews

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Preview(
    device = device,
    group = lightThemeGroup,
    name = "Light English",
)
@Preview(
    device = device,
    group = lightThemeGroup,
    locale = "es",
    name = "Light Spanish",
)
@Preview(
    device = device,
    group = lightThemeGroup,
    locale = "gl",
    name = "Light Galician",
)
@Preview(
    device = device,
    group = darkThemeGroup,
    name = "Dark English",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
@Preview(
    device = device,
    group = darkThemeGroup,
    locale = "es",
    name = "Dark Spanish",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
@Preview(
    device = device,
    group = darkThemeGroup,
    locale = "gl",
    name = "Dark Galician",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
public annotation class RollerCoastersPreview
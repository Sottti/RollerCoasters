package com.sottti.roller.coasters.presentation.previews

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    device = DEVICE,
    group = LIGHT_THEME_GROUP,
    heightDp = 1800,
    name = LIGHT_ENGLISH_NAME,
)
@Preview(
    device = DEVICE,
    group = DARK_THEME_GROUP,
    heightDp = 1800,
    name = DARK_ENGLISH_NAME,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
@Preview(
    device = DEVICE,
    group = LIGHT_THEME_GROUP,
    heightDp = 1800,
    locale = "es",
    name = LIGHT_SPANISH_NAME,
)
@Preview(
    device = DEVICE,
    group = DARK_THEME_GROUP,
    heightDp = 1800,
    locale = "es",
    name = DARK_SPANISH_NAME,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
@Preview(
    device = DEVICE,
    group = LIGHT_THEME_GROUP,
    heightDp = 1800,
    locale = "gl",
    name = LIGHT_GALICIAN_NAME,
)
@Preview(
    device = DEVICE,
    group = DARK_THEME_GROUP,
    heightDp = 1800,
    locale = "gl",
    name = DARK_GALICIAN_NAME,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
public annotation class RollerCoastersTallPreview

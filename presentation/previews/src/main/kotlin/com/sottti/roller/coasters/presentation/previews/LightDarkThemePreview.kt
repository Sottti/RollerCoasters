package com.sottti.roller.coasters.presentation.previews

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Preview(group = lightThemeGroup, device = device)
@Preview(group = darkThemeGroup, device = device, uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
public annotation class LightDarkThemePreview
package com.sottti.roller.coasters.presentation.previews

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers.BLUE_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.RED_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.YELLOW_DOMINATED_EXAMPLE

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Preview(group = lightThemeGroup, device = device, name = "Red", wallpaper = RED_DOMINATED_EXAMPLE)
@Preview(
    group = lightThemeGroup,
    device = device,
    name = "Blue",
    wallpaper = BLUE_DOMINATED_EXAMPLE,
)
@Preview(
    group = lightThemeGroup,
    device = device,
    name = "Green",
    wallpaper = GREEN_DOMINATED_EXAMPLE,
)
@Preview(
    group = lightThemeGroup,
    device = device,
    name = "Yellow",
    wallpaper = YELLOW_DOMINATED_EXAMPLE,
)

@Preview(
    group = darkThemeGroup,
    device = device,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    name = "Red",
    wallpaper = RED_DOMINATED_EXAMPLE,
)
@Preview(
    group = darkThemeGroup,
    device = device,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    name = "Blue",
    wallpaper = BLUE_DOMINATED_EXAMPLE,
)
@Preview(
    group = darkThemeGroup,
    device = device,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    name = "Green",
    wallpaper = GREEN_DOMINATED_EXAMPLE,
)
@Preview(
    group = darkThemeGroup,
    device = device,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    name = "Yellow",
    wallpaper = YELLOW_DOMINATED_EXAMPLE,
)
public annotation class DynamicColorsPreview
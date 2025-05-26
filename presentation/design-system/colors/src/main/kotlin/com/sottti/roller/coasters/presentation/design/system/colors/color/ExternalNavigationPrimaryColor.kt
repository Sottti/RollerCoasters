package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import java.util.Locale

private const val HOST_ANDROID_DEVELOPERS: String = "developer.android.com"
private const val HOST_DRIBBBLE: String = "dribbble.com"
private const val HOST_GITHUB: String = "github.com"
private const val HOST_INSTAGRAM: String = "instagram.com"
private const val HOST_LINKEDIN: String = "linkedin.com"
private const val HOST_MEDIUM: String = "medium.com"
private const val HOST_STACK_OVERFLOW: String = "stackoverflow.com"
private const val HOST_TWITTER: String = "twitter.com"
private const val HOST_X: String = "x.com"


@ColorInt
@Composable
@ReadOnlyComposable
public fun externalNavigationPrimaryColor(@StringRes url: Int): Color {

    val host: String = stringResource(url)
        .toUri().host?.lowercase(Locale.US) ?: return fallbackColor()

    return when {
        host.endsWith(HOST_ANDROID_DEVELOPERS) -> androidDevelopersColor()
        host.endsWith(HOST_DRIBBBLE) -> androidDevelopersColor()
        host.endsWith(HOST_GITHUB) -> gitHubColor()
        host.endsWith(HOST_INSTAGRAM) -> instagramColor()
        host.endsWith(HOST_LINKEDIN) -> linkedInColor()
        host.endsWith(HOST_MEDIUM) -> mediumColor()
        host.endsWith(HOST_STACK_OVERFLOW) -> stackOverflowColor()
        host.endsWith(HOST_TWITTER) || host.endsWith(HOST_X) -> xColor()
        else -> fallbackColor()
    }
}
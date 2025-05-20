package com.sottti.roller.coasters.presentation.about.me.data

import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import com.sottti.roller.coasters.presentation.design.system.colors.color.fallbackColor
import com.sottti.roller.coasters.presentation.design.system.colors.color.gitHubColor
import com.sottti.roller.coasters.presentation.design.system.colors.color.instagramColor
import com.sottti.roller.coasters.presentation.design.system.colors.color.linkedInColor
import com.sottti.roller.coasters.presentation.design.system.colors.color.mediumColor
import com.sottti.roller.coasters.presentation.design.system.colors.color.stackOverflowColor
import com.sottti.roller.coasters.presentation.design.system.colors.color.xColor
import java.util.Locale

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
internal fun socialNetworkPrimaryColor(@StringRes url: Int): Color {

    val host: String = stringResource(url)
        .toUri().host?.lowercase(Locale.US) ?: return fallbackColor()

    return when {
        host.endsWith(HOST_GITHUB) -> gitHubColor()
        host.endsWith(HOST_INSTAGRAM) -> instagramColor()
        host.endsWith(HOST_LINKEDIN) -> linkedInColor()
        host.endsWith(HOST_MEDIUM) -> mediumColor()
        host.endsWith(HOST_STACK_OVERFLOW) -> stackOverflowColor()
        host.endsWith(HOST_TWITTER) || host.endsWith(HOST_X) -> xColor()
        else -> fallbackColor()
    }
}
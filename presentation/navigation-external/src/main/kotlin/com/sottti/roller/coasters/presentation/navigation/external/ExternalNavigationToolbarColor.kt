package com.sottti.roller.coasters.presentation.navigation.external

import androidx.annotation.ColorInt
import androidx.core.net.toUri
import java.util.Locale

private const val HOST_GITHUB: String = "github.com"
private const val HOST_INSTAGRAM: String = "instagram.com"
private const val HOST_LINKEDIN: String = "linkedin.com"
private const val HOST_MEDIUM: String = "medium.com"
private const val HOST_STACK_OVERFLOW: String = "stackoverflow.com"
private const val HOST_TWITTER: String = "twitter.com"
private const val HOST_X: String = "x.com"

@ColorInt
private const val COLOR_GITHUB: Int = 0xFF26292E.toInt()

@ColorInt
private const val COLOR_LINKEDIN: Int = 0xFFFFFFFF.toInt()

@ColorInt
private const val COLOR_STACK_OVERFLOW: Int = 0xFFFFFFFF.toInt()

@ColorInt
private const val COLOR_INSTAGRAM: Int = 0xFFFFFFFF.toInt()

@ColorInt
private const val COLOR_X: Int = 0xFFFFFFFF.toInt()

@ColorInt
private const val COLOR_MEDIUM: Int = 0xFFFFFFFF.toInt()

@ColorInt
internal fun String.toolbarColor(): Int? {
    val host: String = toUri().host?.lowercase(Locale.US) ?: return null
    return when {
        host.endsWith(HOST_GITHUB) -> COLOR_GITHUB
        host.endsWith(HOST_INSTAGRAM) -> COLOR_INSTAGRAM
        host.endsWith(HOST_LINKEDIN) -> COLOR_LINKEDIN
        host.endsWith(HOST_MEDIUM) -> COLOR_MEDIUM
        host.endsWith(HOST_STACK_OVERFLOW) -> COLOR_STACK_OVERFLOW
        host.endsWith(HOST_TWITTER) || host.endsWith(HOST_X) -> COLOR_X
        else -> null
    }
}
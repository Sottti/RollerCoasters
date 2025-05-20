package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.annotation.ColorInt
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

private val FALLBACK_COLOR_DARK: Color = Color(0xFF000000)
private val FALLBACK_COLOR_LIGHT: Color = Color(0xFFFFFFFF)
private val GITHUB_PRIMARY_COLOR_DARK: Color = Color(0xFF020409)
private val GITHUB_PRIMARY_COLOR_LIGHT: Color = Color(0xFFF6F8FA)
private val INSTAGRAM_PRIMARY_COLOR_DARK: Color = Color(0xFF000000)
private val INSTAGRAM_PRIMARY_COLOR_LIGHT: Color = Color(0xFFFFFFFF)
private val LINKED_IN_PRIMARY_COLOR_DARK: Color = Color(0xFF1E2226)
private val LINKED_IN_PRIMARY_COLOR_LIGHT: Color = Color(0xFFFFFFFF)
private val MEDIUM_PRIMARY_COLOR_DARK: Color = Color(0xFFFFFFFF)
private val MEDIUM_PRIMARY_COLOR_LIGHT: Color = Color(0xFFFFFFFF)
private val STACK_OVERFLOW_PRIMARY_COLOR_DARK: Color = Color(0xFF252627)
private val STACK_OVERFLOW_PRIMARY_COLOR_LIGHT: Color = Color(0xFFFFFFFF)
private val X_PRIMARY_COLOR_DARK: Color = Color(0xFF000000)
private val X_PRIMARY_COLOR_LIGHT: Color = Color(0xFFFFFFFF)

@ColorInt
@Composable
@ReadOnlyComposable
public fun gitHubColor(): Color =
    when {
        isSystemInDarkTheme() -> GITHUB_PRIMARY_COLOR_DARK
        else -> GITHUB_PRIMARY_COLOR_LIGHT
    }

@ColorInt
@Composable
@ReadOnlyComposable
public fun linkedInColor(): Color =
    when {
        isSystemInDarkTheme() -> LINKED_IN_PRIMARY_COLOR_DARK
        else -> LINKED_IN_PRIMARY_COLOR_LIGHT
    }

@ColorInt
@Composable
@ReadOnlyComposable
public fun stackOverflowColor(): Color =
    when {
        isSystemInDarkTheme() -> STACK_OVERFLOW_PRIMARY_COLOR_DARK
        else -> STACK_OVERFLOW_PRIMARY_COLOR_LIGHT
    }

@ColorInt
@Composable
@ReadOnlyComposable
public fun instagramColor(): Color =
    when {
        isSystemInDarkTheme() -> INSTAGRAM_PRIMARY_COLOR_DARK
        else -> INSTAGRAM_PRIMARY_COLOR_LIGHT
    }

@ColorInt
@Composable
@ReadOnlyComposable
public fun xColor(): Color =
    when {
        isSystemInDarkTheme() -> X_PRIMARY_COLOR_DARK
        else -> X_PRIMARY_COLOR_LIGHT
    }

@ColorInt
@Composable
@ReadOnlyComposable
public fun mediumColor(): Color =
    when {
        isSystemInDarkTheme() -> MEDIUM_PRIMARY_COLOR_DARK
        else -> MEDIUM_PRIMARY_COLOR_LIGHT
    }

@ColorInt
@Composable
@ReadOnlyComposable
public fun fallbackColor(): Color =
    when {
        isSystemInDarkTheme() -> FALLBACK_COLOR_DARK
        else -> FALLBACK_COLOR_LIGHT
    }
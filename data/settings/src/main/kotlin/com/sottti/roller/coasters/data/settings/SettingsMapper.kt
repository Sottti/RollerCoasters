package com.sottti.roller.coasters.data.settings

import android.app.UiModeManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.os.LocaleListCompat.forLanguageTags
import androidx.core.os.LocaleListCompat.getEmptyLocaleList
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.Language
import com.sottti.roller.coasters.data.settings.model.Theme
import java.util.Locale

private const val LOCALE_GALICIA_TAG = "gl-ES"
private const val LOCALE_SPAIN_TAG = "es-ES"
private const val LOCALE_UK_TAG = "en-GB"

@RequiresApi(Build.VERSION_CODES.R)
internal fun Theme.toUiModeManagerNightMode() = when (this) {
    Theme.DarkTheme -> UiModeManager.MODE_NIGHT_YES
    Theme.LightTheme -> UiModeManager.MODE_NIGHT_NO
    Theme.SystemTheme -> UiModeManager.MODE_NIGHT_CUSTOM
}

internal fun Theme.toAppCompatDelegateNightMode() = when (this) {
    Theme.DarkTheme -> AppCompatDelegate.MODE_NIGHT_YES
    Theme.LightTheme -> AppCompatDelegate.MODE_NIGHT_NO
    Theme.SystemTheme -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
}

internal val Theme.key: String
    get() = when (this) {
        Theme.DarkTheme -> "dark"
        Theme.LightTheme -> "light"
        Theme.SystemTheme -> "system"
    }

internal val ColorContrast.key: String
    get() = when (this) {
        ColorContrast.HighContrast -> "high"
        ColorContrast.MediumContrast -> "medium"
        ColorContrast.StandardContrast -> "standard"
        ColorContrast.SystemContrast -> "system"
    }

internal fun Locale?.toLanguage(): Language =
    when (this?.toLanguageTag()) {
        LOCALE_UK_TAG -> Language.EnglishGbLanguage
        LOCALE_GALICIA_TAG -> Language.GalicianLanguage
        LOCALE_SPAIN_TAG -> Language.SpanishSpainLanguage
        else -> Language.SystemLanguage
    }

internal fun Language.toLocaleList(): LocaleListCompat =
    when (this) {
        Language.EnglishGbLanguage -> forLanguageTags(LOCALE_UK_TAG)
        Language.GalicianLanguage -> forLanguageTags("$LOCALE_GALICIA_TAG,$LOCALE_SPAIN_TAG")
        Language.SpanishSpainLanguage -> forLanguageTags(LOCALE_SPAIN_TAG)
        Language.SystemLanguage -> getEmptyLocaleList()
    }
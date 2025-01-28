package com.sottti.roller.coasters.data.settings

import android.app.UiModeManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.data.settings.model.Theme
import com.sottti.roller.coasters.utils.device.sdk.isAtLeastSdk31
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class ThemeManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    internal fun setTheme(theme: Theme) {
        when {
            isAtLeastSdk31() -> context
                .getSystemService(UiModeManager::class.java)
                ?.setApplicationNightMode(theme.toUiModeManagerNightMode())

            else -> AppCompatDelegate.setDefaultNightMode(theme.toAppCompatDelegateNightMode())
        }
    }
}
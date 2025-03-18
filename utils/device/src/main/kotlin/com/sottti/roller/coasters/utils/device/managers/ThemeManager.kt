package com.sottti.roller.coasters.utils.device.managers

import android.app.UiModeManager
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.domain.model.Theme
import com.sottti.roller.coasters.utils.device.mappers.toAppCompatDelegateNightMode
import com.sottti.roller.coasters.utils.device.mappers.toUiModeManagerNightMode
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import javax.inject.Inject

public class ThemeManager @Inject constructor(
    private val sdkFeatures: SdkFeatures,
    private val uiModeManager: UiModeManager?,
) {
    public fun setTheme(theme: Theme) {
        when {
            sdkFeatures.setPersistentNightModeAvailable() ->
                uiModeManager?.setApplicationNightMode(theme.toUiModeManagerNightMode())

            else -> AppCompatDelegate.setDefaultNightMode(theme.toAppCompatDelegateNightMode())
        }
    }
}
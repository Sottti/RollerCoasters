package com.sottti.roller.coasters.data.settings.helpers

import android.app.UiModeManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.data.settings.mappers.toAppCompatDelegateNightMode
import com.sottti.roller.coasters.data.settings.mappers.toUiModeManagerNightMode
import com.sottti.roller.coasters.domain.model.Theme
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class UiModeManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sdkFeatures: SdkFeatures,
) {
    internal fun setTheme(theme: Theme) {
        when {
            sdkFeatures.setPersistentNightModeAvailable() ->
                context
                    .getSystemService(UiModeManager::class.java)
                    ?.setApplicationNightMode(theme.toUiModeManagerNightMode())

            else -> AppCompatDelegate.setDefaultNightMode(theme.toAppCompatDelegateNightMode())
        }
    }
}
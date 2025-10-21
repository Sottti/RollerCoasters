package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.data.settings.mapper.toAppCompatDelegateNightMode
import com.sottti.roller.coasters.data.settings.mapper.toUiModeManagerNightMode
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.SystemTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ThemeManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val features: Features,
    private val uiModeManager: UiModeManager?,
) {
    fun setTheme(appTheme: AppTheme) {
        when {
            features.setPersistentNightModeAvailable() ->
                uiModeManager?.setApplicationNightMode(appTheme.toUiModeManagerNightMode())

            else -> AppCompatDelegate.setDefaultNightMode(appTheme.toAppCompatDelegateNightMode())
        }
    }

    fun getSystemTheme(): SystemTheme =
        when (Configuration.UI_MODE_NIGHT_YES) {
            (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ->
                SystemTheme.DarkSystemTheme

            else -> SystemTheme.LightSystemTheme
        }

    fun observeSystemTheme(): Flow<SystemTheme> = callbackFlow {
        trySend(getSystemTheme())

        val callbacks = object : ComponentCallbacks {
            override fun onConfigurationChanged(newConfig: Configuration) {
                trySend(getSystemTheme())
            }

            @Deprecated("Deprecated in Java")
            override fun onLowMemory() = Unit
        }

        context.registerComponentCallbacks(callbacks)
        awaitClose { context.unregisterComponentCallbacks(callbacks) }
    }
        .distinctUntilChanged()
        .conflate()
}

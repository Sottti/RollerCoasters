package com.sottti.roller.coasters.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.sottti.roller.coasters.data.settings.repository.SettingsRepository
import com.sottti.roller.coasters.presentation.home.ui.startHomeActivity
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
internal class SplashScreenActivity : ComponentActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    @Inject
    lateinit var sdkFeatures: SdkFeatures

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null && !sdkFeatures.setPersistentNightModeAvailable()) {
            lifecycleScope.launch { settingsRepository.applyStoredTheme() }
        }
        startHomeActivity(this)
        finish()
    }
}
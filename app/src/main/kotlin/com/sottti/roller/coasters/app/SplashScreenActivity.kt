package com.sottti.roller.coasters.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.sottti.roller.coasters.domain.settings.usecase.ApplyStoredTheme
import com.sottti.roller.coasters.presentation.home.ui.startHomeActivity
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
internal class SplashScreenActivity : ComponentActivity() {

    @Inject
    lateinit var applyStoredTheme: ApplyStoredTheme

    @Inject
    lateinit var sdkFeatures: SdkFeatures

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null && !sdkFeatures.setPersistentNightModeAvailable()) {
            lifecycleScope.launch { applyStoredTheme() }
        }
        startHomeActivity(this)
        finish()
    }
}
package com.sottti.roller.coasters.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.sottti.roller.coasters.app.ui.MainActivity
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import com.sottti.roller.coasters.utils.device.isBelowSdk31
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
internal class SplashScreenActivity : ComponentActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null && isBelowSdk31()) {
            lifecycleScope.launch { settingsRepository.applyStoredAppTheme() }
        }
        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        finish()
    }
}
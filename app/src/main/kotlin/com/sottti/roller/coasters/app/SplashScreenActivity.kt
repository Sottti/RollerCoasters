package com.sottti.roller.coasters.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ScheduleRollerCoastersSync
import com.sottti.roller.coasters.domain.settings.usecase.theme.ApplyStoredAppTheme
import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import com.sottti.roller.coasters.presentation.home.ui.startHomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
internal class SplashScreenActivity : ComponentActivity() {

    @Inject
    lateinit var applyStoredAppTheme: ApplyStoredAppTheme

    @Inject
    lateinit var systemFeatures: SystemFeatures

    @Inject
    lateinit var scheduleRollerCoastersSync: ScheduleRollerCoastersSync

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null && !systemFeatures.setPersistentNightModeAvailable()) {
            lifecycleScope.launch { applyStoredAppTheme() }
        }

        scheduleRollerCoastersSync()

        startHomeActivity(context = this)

        finish()
    }
}

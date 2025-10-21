package com.sottti.roller.coasters.presentation.home.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.domain.settings.di.ThemeChangeSignal
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sottti.roller.coasters.presentation.home.data.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal open class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var themeChangeSignal: ThemeChangeSignal

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.state.collectAsStateWithLifecycle().value.let { state ->
                RollerCoastersTheme(
                    colorContrast = state.colorContrast,
                    dynamicColor = state.dynamicColor,
                ) {
                    HomeUi(state = state, onAction = viewModel.onAction)
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (themeChangeSignal.activityRecreationNeeded) {
            themeChangeSignal.activityRecreationNeeded = false
            recreate()
        }
    }
}

public fun startHomeActivity(context: Context) {
    context.startActivity(Intent(context, HomeActivity::class.java))
}

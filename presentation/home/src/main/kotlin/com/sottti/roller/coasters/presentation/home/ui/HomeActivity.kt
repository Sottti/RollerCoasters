package com.sottti.roller.coasters.presentation.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sottti.roller.coasters.presentation.home.data.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal open class HomeActivityBase : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<HomeViewModel>()
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
}

internal class HomeActivityWithComposeUiModeTracking : HomeActivityBase()

internal class HomeActivityWithoutComposeUiModeTracking : HomeActivityBase()

public fun startHomeActivity(
    context: Context,
    composeUiModeTrackingAvailable: Boolean,
) {
    val activity = when (composeUiModeTrackingAvailable) {
        true -> HomeActivityWithComposeUiModeTracking::class.java
        false -> HomeActivityWithoutComposeUiModeTracking::class.java
    }
    context.startActivity(Intent(context, activity))
}

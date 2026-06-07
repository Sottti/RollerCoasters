package com.sottti.roller.coasters.presentation.app.shell.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sottti.roller.coasters.presentation.app.shell.data.AppShellViewModel
import com.sottti.roller.coasters.presentation.app.shell.data.toColorContrast
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal open class AppShellActivity : AppCompatActivity() {

    private val viewModel: AppShellViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.state.collectAsStateWithLifecycle().value.let { state ->
                RollerCoastersTheme(
                    colorContrast = state.colorContrast.toColorContrast(),
                    useDynamicColor = state.dynamicColor.enabled,
                    useDarkTheme = state.theme == ResolvedTheme.DarkResolvedTheme,
                ) {
                    AppShellUi(state = state, onAction = viewModel.onAction)
                }
            }
        }
    }
}

public fun startAppShellActivity(context: Context) {
    context.startActivity(Intent(context, AppShellActivity::class.java))
}

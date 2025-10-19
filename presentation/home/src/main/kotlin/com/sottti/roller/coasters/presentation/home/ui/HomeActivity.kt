package com.sottti.roller.coasters.presentation.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveResolvedDynamicColor
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
internal class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var provideObserveResolvedDynamicColor: ObserveResolvedDynamicColor

    @Inject
    lateinit var provideObserveResolvedColorContrast: ObserveResolvedColorContrast

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(
                    flow = provideObserveResolvedColorContrast(),
                    flow2 = provideObserveResolvedDynamicColor(),
                ) { resolvedColorContrast, resolvedDynamicColor ->
                    resolvedColorContrast to resolvedDynamicColor
                }
                    .distinctUntilChanged()
                    .collectLatest { (resolvedColorContrast, resolvedDynamicColor) ->
                        setContent {
                            RollerCoastersTheme(
                                colorContrast = resolvedColorContrast,
                                dynamicColor = resolvedDynamicColor
                            ) { HomeUi() }
                        }
                    }
            }
        }
    }
}

public fun startHomeActivity(context: Context) {
    context.startActivity(Intent(context, HomeActivity::class.java))
}

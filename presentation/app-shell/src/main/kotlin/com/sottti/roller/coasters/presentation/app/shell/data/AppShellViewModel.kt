package com.sottti.roller.coasters.presentation.app.shell.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.theme.GetSystemTheme
import com.sottti.roller.coasters.domain.settings.usecase.theme.ObserveResolvedTheme
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellActions
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellActions.NoOp
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellState
import com.sottti.roller.coasters.presentation.utils.stateInWhileSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan
import javax.inject.Inject

@HiltViewModel
internal class AppShellViewModel @Inject constructor(
    getSystemThemeTheme: GetSystemTheme,
    observeResolvedColorContrast: ObserveResolvedColorContrast,
    observeResolvedDynamicColor: ObserveResolvedDynamicColor,
    observeResolvedTheme: ObserveResolvedTheme,
) : ViewModel() {

    val initialState = initialState(getSystemThemeTheme())

    private val actions = MutableSharedFlow<AppShellActions>(extraBufferCapacity = 64)
    val state: StateFlow<AppShellState> = combine(
        flow = observeResolvedColorContrast(),
        flow2 = observeResolvedDynamicColor(),
        flow3 = observeResolvedTheme(),
        flow4 = actions.onStart { emit(NoOp) },
    ) { resolvedColorContrast, resolvedDynamicColor, resolvedTheme, stateMutationAction ->
        reducer(
            resolvedColorContrast,
            resolvedDynamicColor,
            resolvedTheme,
            stateMutationAction,
        )
    }.scan(initialState) { previous, reduce -> reduce(previous) }
        .drop(1)
        .stateInWhileSubscribed(initialValue = initialState)

    internal val onAction: (AppShellActions) -> Unit = ::processAction
    private fun processAction(action: AppShellActions) = actions.tryEmit(action)
    private val reducer: (
        resolvedColorContrast: ResolvedColorContrast,
        resolvedDynamicColor: ResolvedDynamicColor,
        resolvedTheme: ResolvedTheme,
        stateMutationAction: AppShellActions,
    ) -> (AppShellState) -> AppShellState =
        { resolvedColorContrast, resolvedDynamicColor, resolvedTheme, stateMutationAction ->
            { previous: AppShellState ->
                previous.reduce(
                    resolvedColorContrast = resolvedColorContrast,
                    resolvedDynamicColor = resolvedDynamicColor,
                    resolvedTheme = resolvedTheme,
                    stateMutationAction = stateMutationAction,
                )
            }
        }
}

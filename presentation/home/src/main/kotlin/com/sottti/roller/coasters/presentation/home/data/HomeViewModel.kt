package com.sottti.roller.coasters.presentation.home.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveResolvedDynamicColor
import com.sottti.roller.coasters.presentation.home.model.HomeActions
import com.sottti.roller.coasters.presentation.home.model.HomeActions.NoOp
import com.sottti.roller.coasters.presentation.home.model.HomeState
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
internal class HomeViewModel @Inject constructor(
    observeResolvedColorContrast: ObserveResolvedColorContrast,
    observeResolvedDynamicColor: ObserveResolvedDynamicColor,
) : ViewModel() {

    private val actions = MutableSharedFlow<HomeActions>(extraBufferCapacity = 64)
    val state: StateFlow<HomeState> = combine(
        flow = observeResolvedColorContrast(),
        flow2 = observeResolvedDynamicColor(),
        flow3 = actions.onStart { emit(NoOp) },
    ) { resolvedColorContrast, resolvedDynamicColor, stateMutationAction ->
        reducer(
            resolvedColorContrast,
            resolvedDynamicColor,
            stateMutationAction,
        )
    }.scan(initialState) { previous, reduce -> reduce(previous) }
        .drop(1)
        .stateInWhileSubscribed(initialValue = initialState)

    internal val onAction: (HomeActions) -> Unit = ::processAction
    private fun processAction(action: HomeActions) = actions.tryEmit(action)
    private val reducer: (
        resolvedColorContrast: ResolvedColorContrast,
        resolvedDynamicColor: ResolvedDynamicColor,
        stateMutationAction: HomeActions,
    ) -> (HomeState) -> HomeState =
        { resolvedColorContrast, resolvedDynamicColor, stateMutationAction ->
            { previous: HomeState ->
                previous.reduce(
                    resolvedDynamicColor = resolvedDynamicColor,
                    stateMutationAction = stateMutationAction,
                    resolvedColorContrast = resolvedColorContrast,
                )
            }
        }
}

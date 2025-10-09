package com.sottti.roller.coasters.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

context(viewModel: ViewModel)
public fun <T> Flow<T>.stateInWhileSubscribed(
    initialValue: T,
): StateFlow<T> = stateIn(
    scope = viewModel.viewModelScope,
    started = WhileSubscribed(stopTimeoutMillis = 5_000),
    initialValue = initialValue,
)

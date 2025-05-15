package com.sottti.roller.coasters.presentation.about.me.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class AboutMeViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    internal val state: StateFlow<AboutMeState> = _state.asStateFlow()

    internal val onAction: (AboutMeAction) -> Unit = { action -> processAction(action) }

    private fun processAction(action: AboutMeAction) {

    }
}
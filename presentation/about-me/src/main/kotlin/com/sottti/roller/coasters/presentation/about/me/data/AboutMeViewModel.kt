package com.sottti.roller.coasters.presentation.about.me.data

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction.OpenUrl
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.navigation.external.ExternalNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class AboutMeViewModel @Inject constructor(
    val externalNavigation: ExternalNavigation,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    internal val state: StateFlow<AboutMeState> = _state.asStateFlow()

    internal val onAction: (AboutMeAction) -> Unit = { action -> processAction(action) }

    private fun processAction(action: AboutMeAction) {
        when (action) {
            is OpenUrl -> externalNavigation.openUrl(
                urlResId = action.urlResId,
                toolbarColor = action.primaryColor.toArgb(),
            )
        }
    }
}

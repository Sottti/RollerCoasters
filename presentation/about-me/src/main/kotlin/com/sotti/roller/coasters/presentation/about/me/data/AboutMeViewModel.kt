package com.sotti.roller.coasters.presentation.about.me.data

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.sotti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sotti.roller.coasters.presentation.about.me.model.AboutMeAction.OpenUrl
import com.sotti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sotti.roller.coasters.presentation.navigation.external.ExternalNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class AboutMeViewModel @Inject constructor(
    val externalNavigation: ExternalNavigation,
) : ViewModel() {

    internal val state: StateFlow<AboutMeState> =
        MutableStateFlow(initialState).asStateFlow()

    internal val onAction: (AboutMeAction) -> Unit = ::processAction

    private fun processAction(action: AboutMeAction) =
        when (action) {
            is OpenUrl -> externalNavigation.openUrl(
                urlResId = action.urlResId,
                toolbarColor = action.primaryColor.toArgb(),
            )
        }
}

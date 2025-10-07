package com.sotti.roller.coasters.presentation.settings.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.sottti.roller.coasters.presentation.settings.ui.SettingsUiPreview
import com.sottti.roller.coasters.presentation.settings.ui.SettingsUiStateProvider
import org.junit.Rule
import org.junit.Test

internal class SettingsUiTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun something_is_visible() {

        rule.setContent { SettingsUiPreview(SettingsUiStateProvider().values.first()) }

        rule
            .onNodeWithText("Settings")
            .assertIsDisplayed()

    }
}

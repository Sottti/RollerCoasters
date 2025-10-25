package com.sotti.roller.coasters.presentation.design.system.card.grid

import android.R
import androidx.activity.ComponentActivity
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.FontScale
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.google.common.truth.Truth.assertThat
import com.sotti.roller.coasters.presentation.design.system.card.grid.MonoCardGridPreview
import com.sotti.roller.coasters.presentation.design.system.card.grid.monoCardGridState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class MonoCardGridUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var text: String

    @Before
    fun setup() {
        text = rule.activity.getString(monoCardGridState.textResId)
    }

    @Test
    fun displays_given_text() {
        val textResId = R.string.copy
        rule.setContent { MonoCardGridPreview(monoCardGridState.copy(textResId = textResId)) }

        rule
            .nodeWithClickableText(rule.activity.getString(textResId))
            .assertIsDisplayed()
    }

    @Test
    fun invokes_onClick() {
        var clicks = 0

        rule.setContent { MonoCardGridPreview(monoCardGridState.copy(onClick = { clicks++ })) }

        rule
            .nodeWithClickableText(text)
            .assertIsDisplayed()
            .performClick()

        assertThat(clicks).isEqualTo(1)
    }

    @Test
    fun respects_external_modifier_testTag() {
        val tag = "mono_card"

        rule.setContent {
            MonoCardGridPreview(monoCardGridState.copy(modifier = Modifier.testTag(tag)))
        }

        rule
            .onNodeWithTag(tag)
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun meets_min_touch_target() {
        var minTouchTargetSize: Dp = Int.MAX_VALUE.dp

        rule.setContent {
            minTouchTargetSize = LocalMinimumInteractiveComponentSize.current
            MonoCardGridPreview(monoCardGridState)
        }

        rule
            .onNodeWithText(text)
            .assertWidthIsAtLeast(minTouchTargetSize)
            .assertHeightIsAtLeast(minTouchTargetSize)
    }

    @Test
    fun large_font_scale_is_visible() {
        rule.setContent {
            DeviceConfigurationOverride(DeviceConfigurationOverride.FontScale(2f)) {
                MonoCardGridPreview(monoCardGridState)
            }
        }

        rule.assertAllVisible(text)
    }

    @Test
    fun rtl_layout_text_still_found() {
        rule.setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                MonoCardGridPreview(monoCardGridState)
            }
        }

        rule.assertAllVisible(text)
    }
}

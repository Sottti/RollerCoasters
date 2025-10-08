package com.sotti.roller.coasters.presentation.design.system.card.grid

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
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.card.grid.MonoCardGridPreview
import com.sottti.roller.coasters.presentation.design.system.card.grid.R
import com.sottti.roller.coasters.presentation.design.system.card.grid.monoCardGridState
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class MonoCardGridUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var text: String

    @Before
    fun setup() {
        text = rule.activity.getString(R.string.first_item)
    }

    @Test
    fun displays_given_text() {
        val textResId = android.R.string.copy
        rule.setContent { MonoCardGridPreview(monoCardGridState.copy(textResId = textResId)) }

        rule
            .onNode(hasText(rule.activity.getString(textResId)) and hasClickAction())
            .assertIsDisplayed()
    }

    @Test
    fun invokes_onClick() {
        val onClick = mockk<() -> Unit>(relaxed = true)

        rule.setContent { MonoCardGridPreview(monoCardGridState.copy(onClick = onClick)) }

        rule
            .onNode(hasText(text) and hasClickAction())
            .assertIsDisplayed()
            .performClick()

        verify { onClick.invoke() }
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
            DeviceConfigurationOverride(
                DeviceConfigurationOverride.FontScale(2f)
            ) {
                MonoCardGridPreview(monoCardGridState)
            }
        }

        rule
            .onNodeWithText(text)
            .assertIsDisplayed()
    }

    @Test
    fun rtl_layout_text_still_found() {
        rule.setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                MonoCardGridPreview(monoCardGridState)
            }
        }

        rule
            .onNodeWithText(text)
            .assertIsDisplayed()
    }
}

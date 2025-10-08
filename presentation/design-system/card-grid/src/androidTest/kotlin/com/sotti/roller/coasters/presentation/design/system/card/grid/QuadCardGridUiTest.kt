package com.sotti.roller.coasters.presentation.design.system.card.grid

import androidx.activity.ComponentActivity
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.FontScale
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
import com.sottti.roller.coasters.presentation.design.system.card.grid.QuadCardGridPreview
import com.sottti.roller.coasters.presentation.design.system.card.grid.quadCardGridState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class QuadCardGridUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var first: String
    private lateinit var second: String
    private lateinit var third: String
    private lateinit var fourth: String

    @Before
    fun setup() {
        first = rule.activity.getString(quadCardGridState.items.firstItemResId)
        second = rule.activity.getString(quadCardGridState.items.secondItemResId)
        third = rule.activity.getString(quadCardGridState.items.thirdItemResId)
        fourth = rule.activity.getString(quadCardGridState.items.forthItemResId)
    }

    @Test
    fun displays_all_four_labels() {
        rule.setContent { QuadCardGridPreview(quadCardGridState) }

        rule.assertAllVisible(first, second, third, fourth)

        listOf(first, second, third, fourth).forEach {
            rule
                .nodeWithClickableText(it)
                .assertIsDisplayed()
        }
    }

    @Test
    fun each_card_invokes_correct_indices_in_order() {
        val clicks = mutableListOf<Int>()

        rule.setContent {
            QuadCardGridPreview(
                quadCardGridState.copy(
                    onClick = { position -> clicks += position },
                ),
            )
        }

        listOf(first, second, third, fourth).forEach { label ->
            rule
                .nodeWithClickableText(label)
                .performClick()
        }

        assertThat(clicks)
            .containsExactly(0, 1, 2, 3)
            .inOrder()
    }

    @Test
    fun respects_external_modifier_testTag() {
        val tag = "quad_card_grid"

        rule.setContent {
            QuadCardGridPreview(
                quadCardGridState.copy(
                    modifier = Modifier.testTag(tag),
                ),
            )
        }

        rule
            .onNodeWithTag(tag)
            .assertIsDisplayed()
    }

    @Test
    fun each_card_meets_min_touch_target() {
        var minTouchTargetSize: Dp = Int.MAX_VALUE.dp

        rule.setContent {
            minTouchTargetSize = LocalMinimumInteractiveComponentSize.current
            QuadCardGridPreview(quadCardGridState)
        }

        listOf(first, second, third, fourth).forEach { label ->
            rule
                .onNodeWithText(label)
                .assertWidthIsAtLeast(minTouchTargetSize)
                .assertHeightIsAtLeast(minTouchTargetSize)
        }
    }

    @Test
    fun large_font_scale_is_visible() {
        rule.setContent {
            DeviceConfigurationOverride(DeviceConfigurationOverride.FontScale(2f)) {
                QuadCardGridPreview(quadCardGridState)
            }
        }

        rule.assertAllVisible(first, second, third, fourth)
    }

    @Test
    fun rtl_layout_texts_still_found() {
        rule.setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                QuadCardGridPreview(quadCardGridState)
            }
        }

        rule.assertAllVisible(first, second, third, fourth)
    }
}

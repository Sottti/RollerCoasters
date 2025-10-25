package com.sottti.roller.coasters.presentation.design.system.card.grid

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

internal fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>.nodeWithClickableText(
    label: String,
) =
    onNode(hasText(label) and hasClickAction())

internal fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>.assertAllVisible(
    vararg labels: String,
) {
    labels.forEach { onNodeWithText(it).assertIsDisplayed() }
}

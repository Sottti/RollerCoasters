package com.sottti.roller.coasters.presentation.design.system.dimensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.ComponentDimensions
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Dimensions
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.ProgressIndicatorDimensions
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Spacing

@Composable
internal fun compactDimensions() =
    Dimensions(
        spacing = compactSpacing(),
        components = compactComponentDimensions(),
    )

@Composable
internal fun mediumDimensions() =
    Dimensions(
        spacing = mediumSpacing(),
        components = mediumComponentDimensions(),
    )

@Composable
internal fun expandedDimensions() =
    Dimensions(
        spacing = expandedSpacing(),
        components = expandedComponentDimensions(),
    )

@Composable
private fun compactSpacing(): Spacing =
    Spacing(
        small = 2.dp,
        medium = 4.dp,
        large = 8.dp,
    )

@Composable
private fun mediumSpacing(): Spacing = Spacing(
    small = 2.dp,
    medium = 4.dp,
    large = 8.dp,
)

@Composable
private fun expandedSpacing(): Spacing = Spacing(
    small = 2.dp,
    medium = 4.dp,
    large = 8.dp,
)

@Composable
private fun compactComponentDimensions() =
    ComponentDimensions(
        progressIndicator = progressIndicatorCompactDimensions(),
    )

@Composable
private fun mediumComponentDimensions() =
    ComponentDimensions(
        progressIndicator = progressIndicatorMediumDimensions(),
    )

@Composable
private fun expandedComponentDimensions() =
    ComponentDimensions(
        progressIndicator = progressIndicatorExpandedDimensions(),
    )

@Composable
private fun progressIndicatorCompactDimensions(): ProgressIndicatorDimensions =
    ProgressIndicatorDimensions(
        small = 24.dp,
        medium = 36.dp,
        large = 48.dp,
    )

@Composable
private fun progressIndicatorMediumDimensions(): ProgressIndicatorDimensions =
    ProgressIndicatorDimensions(
        small = 24.dp,
        medium = 36.dp,
        large = 48.dp,
    )


@Composable
private fun progressIndicatorExpandedDimensions(): ProgressIndicatorDimensions =
    ProgressIndicatorDimensions(
        small = 24.dp,
        medium = 36.dp,
        large = 48.dp,
    )
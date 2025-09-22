package com.sottti.roller.coasters.presentation.design.system.typography

import androidx.compose.runtime.Composable
import androidx.compose.material3.Typography as MaterialTypography

@Composable
internal fun typography(): Typography =
    with(MaterialTypography()) {
        Typography(
            displayLarge = displayLarge,
            displayMedium = displayMedium,
            displaySmall = displaySmall,
            headlineLarge = headlineLarge,
            headlineMedium = headlineMedium,
            headlineSmall = headlineSmall,
            titleLarge = titleLarge,
            titleMedium = titleMedium,
            titleSmall = titleSmall,
            bodyLarge = bodyLarge,
            bodyMedium = bodyMedium,
            bodySmall = bodySmall,
            labelLarge = labelLarge,
            labelMedium = labelMedium,
            labelSmall = labelSmall,
        )
    }

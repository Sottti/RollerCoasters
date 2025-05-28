package com.sottti.roller.coasters.presentation.design.system.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme

@Composable
internal fun TextPreviewContent() {
    RollerCoastersPreviewTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensions.spacing.large)
                .background(colors.background),
            verticalArrangement = Arrangement.spacedBy(dimensions.spacing.small)
        ) {
            Text.Display.Large(text = "Display Large", textColor = colors.onSurface)
            Text.Display.Medium(text = "Display Medium", textColor = colors.onSurface)
            Text.Display.Small(text = "Display Small", textColor = colors.onSurface)
            Text.Headline.Large(text = "Headline Large", textColor = colors.onSurface)
            Text.Headline.Medium(text = "Headline Medium", textColor = colors.onSurface)
            Text.Headline.Small(text = "Headline Small", textColor = colors.onSurface)
            Text.Title.Large(text = "Title Large", textColor = colors.onSurface)
            Text.Title.Medium(text = "Title Medium", textColor = colors.onSurface)
            Text.Title.Small(text = "Title Small", textColor = colors.onSurface)
            Text.Body.Large(text = "Body Large", textColor = colors.onSurface)
            Text.Body.Medium(text = "Body Medium", textColor = colors.onSurface)
            Text.Body.Small(text = "Body Small", textColor = colors.onSurface)
            Text.Label.Large(text = "Label Large", textColor = colors.onSurface)
            Text.Label.Medium(text = "Label Medium", textColor = colors.onSurface)
            Text.Label.Small(text = "Label Small", textColor = colors.onSurface)
        }
    }
}
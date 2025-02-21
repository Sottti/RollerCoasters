package co.cuvva.presentation.design.system.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme

@Composable
internal fun TextPreviewContent() {
    RollerCoastersPreviewTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensions.spacing.large),
            verticalArrangement = Arrangement.spacedBy(dimensions.spacing.small)
        ) {
            Text.Display.Large(text = "Display Large")
            Text.Display.Medium(text = "Display Medium")
            Text.Display.Small(text = "Display Small")
            Text.Headline.Large(text = "Headline Large")
            Text.Headline.Medium(text = "Headline Medium")
            Text.Headline.Small(text = "Headline Small")
            Text.Title.Large(text = "Title Large")
            Text.Title.Medium(text = "Title Medium")
            Text.Title.Small(text = "Title Small")
            Text.Body.Large(text = "Body Large")
            Text.Body.Medium(text = "Body Medium")
            Text.Body.Small(text = "Body Small")
            Text.Label.Large(text = "Label Large")
            Text.Label.Medium(text = "Label Medium")
            Text.Label.Small(text = "Label Small")
            Text.Vanilla(text = "Vanilla Text")
        }
    }
}
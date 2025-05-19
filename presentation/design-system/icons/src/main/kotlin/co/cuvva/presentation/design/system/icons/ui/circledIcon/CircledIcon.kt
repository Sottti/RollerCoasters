package co.cuvva.presentation.design.system.icons.ui.circledIcon

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import co.cuvva.presentation.design.system.icons.data.Icons
import co.cuvva.presentation.design.system.icons.model.IconState
import co.cuvva.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview

@Composable
public fun CircledIcon(
    iconState: IconState,
    modifier: Modifier = Modifier,
    backgroundColor: Color = colors.background,
    iconColor: Color = colors.onBackground,
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = iconColor,
        ),
    ) {
        Icon(
            modifier = Modifier.padding(dimensions.padding.smallMedium),
            iconState = iconState,
        )
    }
}

@Composable
@LightDarkThemePreview
internal fun CircledIconOnBackgroundPreview() {
    RollerCoastersPreviewTheme {
        CircledIcon(
            iconState = Icons.Android.filled,
            backgroundColor = colors.background,
            iconColor = colors.onBackground,
        )
    }
}

@Composable
@LightDarkThemePreview
internal fun CircledIconOnSurfacePreview() {
    RollerCoastersPreviewTheme {
        CircledIcon(
            iconState = Icons.Android.filled,
            backgroundColor = colors.primaryContainer,
            iconColor = colors.onPrimaryContainer,
        )
    }
}
package com.sottti.roller.coasters.presentation.design.system.illustrations.ui

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.illustrations.data.Illustrations
import com.sottti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
public fun CircledIllustration(
    state: IllustrationState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.aspectRatio(1f),
        shape = CircleShape,
    ) {
        Illustration(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensions.padding.extraSmall)
        )
    }
}

@Composable
@RollerCoastersPreview
internal fun CircledIllustrationPreview() {
    RollerCoastersPreviewTheme {
        CircledIllustration(
            state = Illustrations.DragonKhanAndShambhala.state,
            modifier = Modifier,
        )
    }
}

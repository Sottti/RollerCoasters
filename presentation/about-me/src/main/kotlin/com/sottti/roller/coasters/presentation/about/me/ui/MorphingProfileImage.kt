package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.images.model.ImageState
import com.sottti.roller.coasters.presentation.design.system.images.ui.Image
import com.sottti.roller.coasters.presentation.design.system.shapes.morphPolygonShape
import com.sottti.roller.coasters.presentation.design.system.shapes.shapes

@Composable
internal fun MorphingProfileImage(
    image: ImageState,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val morphPolygonShape = morphPolygonShape(
        startShape = shapes.roundedPolygon.hexagon,
        endShape = shapes.roundedPolygon.octagon,
    )

    Image(
        image,
        modifier = modifier
            .clip(morphPolygonShape)
            .border(
                width = dimensions.spacing.small,
                color = CardDefaults.cardColors().containerColor,
                shape = morphPolygonShape
            )
            .clickable(interactionSource = interactionSource) {}
    )
}

package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.large

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.R
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard.Large
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCardStat
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.image.loading.Image
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import com.sottti.roller.coasters.presentation.utils.Spacer

@Composable
internal fun LargeImpl(
    modifier: Modifier,
    onClick: () -> Unit,
    imageUrl: ImageUrl?,
    foreverLoading: Boolean,
    parkName: String,
    rollerCoasterName: String,
    stat: RollerCoasterCardStat?,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
    ) {
        imageUrl?.let { Image(imageUrl = imageUrl, foreverLoading = foreverLoading) }
        Footer(
            parkName = parkName,
            rollerCoasterName = rollerCoasterName,
            stat = stat,
        )
    }
}

@Composable
private fun Image(
    imageUrl: ImageUrl,
    foreverLoading: Boolean,
) {
    Image(
        url = imageUrl,
        contentDescription = stringResource(R.string.image_content_description),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.75f)
            .padding(dimensions.padding.small),
        foreverLoading = foreverLoading,
        roundedCorners = true,
    )
}

@Composable
private fun Footer(
    parkName: String,
    rollerCoasterName: String,
    stat: RollerCoasterCardStat?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = dimensions.padding.medium)
            .padding(vertical = dimensions.padding.medium)
    ) {
        Column(modifier = Modifier.weight(1.0f)) {
            Text.Title.Large(
                text = rollerCoasterName,
                textColor = colors.onSurface,
            )
            Text.Body.Small(
                text = parkName,
                textColor = colors.onSurfaceVariant,
            )
        }
        Spacer(dimensions.padding.smallMedium)
        stat?.let {
            Column(horizontalAlignment = Alignment.End) {
                Text.Label.Small(
                    text = stat.value,
                    textColor = colors.onSurfaceVariant,
                )
                Text.Label.Medium(
                    text = stat.detail,
                    textColor = colors.onSurface,
                )
            }
        }
    }
}


@Composable
@RollerCoastersPreview
internal fun RollerCoasterCardLargePreview(
    @PreviewParameter(RollerCoasterCardLargeStateProvider::class)
    state: RollerCoasterCardLargeState,
) {
    RollerCoastersPreviewTheme {
        Large(
            imageUrl = state.imageUrl,
            parkName = state.parkName,
            rollerCoasterName = state.rollerCoasterName,
            stat = state.stat,
            foreverLoading = state.foreverLoading,
            onClick = {},
        )
    }
}
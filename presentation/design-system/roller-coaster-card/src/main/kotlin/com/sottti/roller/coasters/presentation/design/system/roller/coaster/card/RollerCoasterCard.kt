package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.R
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.fixtures.imageUrl
import com.sottti.roller.coasters.presentation.fixtures.parkName
import com.sottti.roller.coasters.presentation.fixtures.rollerCoasterName
import com.sottti.roller.coasters.presentation.fixtures.stat
import com.sottti.roller.coasters.presentation.fixtures.statDetail
import com.sottti.roller.coasters.presentation.image.loading.Image
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview

@Composable
public fun RollerCoasterCard(
    imageUrl: ImageUrl?,
    parkName: String,
    rollerCoasterName: String,
    stat: String?,
    statDetail: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    foreverLoading: Boolean = false,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
    ) {
        imageUrl?.let { Image(imageUrl = imageUrl, foreverLoading = foreverLoading) }
        Footer(
            parkName = parkName,
            rollerCoasterName = rollerCoasterName,
            stat = stat,
            statDetail = statDetail,
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
            .aspectRatio(1.75f),
        foreverLoading = foreverLoading,
    )
}

@Composable
private fun Footer(
    parkName: String,
    rollerCoasterName: String,
    stat: String?,
    statDetail: String,
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
            Text.Label.Medium(
                text = parkName,
                textColor = colors.onSurfaceVariant,
            )
        }
        Spacer(modifier = Modifier.size(dimensions.padding.smallMedium))
        stat?.let {
            Column(horizontalAlignment = Alignment.End) {
                Text.Label.Small(
                    text = statDetail,
                    textColor = colors.onSurfaceVariant,
                )
                Text.Label.Medium(
                    text = stat,
                    textColor = colors.onSurface,
                )
            }
        }
    }
}


@Composable
@LightDarkThemePreview
internal fun RollerCoasterCardPreview(
    @PreviewParameter(RollerCoasterCardPreviewProvider::class)
    foreverLoading: Boolean,
) {
    RollerCoastersPreviewTheme {
        RollerCoasterCard(
            imageUrl = imageUrl,
            parkName = parkName,
            rollerCoasterName = rollerCoasterName,
            stat = stat,
            statDetail = statDetail,
            foreverLoading = foreverLoading,
            onClick = {},
        )
    }
}
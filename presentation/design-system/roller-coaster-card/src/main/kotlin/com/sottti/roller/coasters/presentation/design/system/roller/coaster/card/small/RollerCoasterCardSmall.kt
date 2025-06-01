package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.small

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.R
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCard.Small
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.image.loading.Image
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview

@Composable
internal fun SmallImpl(
    foreverLoading: Boolean,
    imageUrl: ImageUrl?,
    modifier: Modifier,
    onClick: () -> Unit,
    parkName: String,
    rollerCoasterName: String,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            imageUrl?.let { Image(imageUrl = imageUrl, foreverLoading = foreverLoading) }
            Details(rollerCoasterName, parkName)
        }
    }
}

@Composable
private fun RowScope.Details(
    rollerCoasterName: String,
    parkName: String,
) {
    Column(
        modifier = Modifier
            .weight(1.0f)
            .padding(dimensions.padding.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text.Title.Large(text = rollerCoasterName)
        Text.Body.Small(text = parkName)
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
            .fillMaxWidth(0.33f)
            .aspectRatio(1.5f)
            .padding(dimensions.padding.small),
        foreverLoading = foreverLoading,
        roundedCorners = true,
    )
}

@Composable
@LightDarkThemePreview
internal fun RollerCoasterCardSmallPreview(
    @PreviewParameter(RollerCoasterCardSmallPreviewProvider::class)
    state: RollerCoasterCardSmallState,
) {
    RollerCoastersPreviewTheme {
        Small(
            modifier = Modifier.fillMaxWidth(),
            imageUrl = state.imageUrl,
            parkName = state.parkName,
            rollerCoasterName = state.rollerCoasterName,
            foreverLoading = state.foreverLoading,
            onClick = {},
        )
    }
}
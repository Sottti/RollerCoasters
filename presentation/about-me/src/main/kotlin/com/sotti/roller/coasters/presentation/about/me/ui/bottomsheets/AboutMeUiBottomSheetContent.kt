package com.sotti.roller.coasters.presentation.about.me.ui.bottomsheets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sotti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sotti.roller.coasters.presentation.about.me.model.AboutMeAction.OpenUrl
import com.sotti.roller.coasters.presentation.about.me.model.AboutMeBottomSheetPreviewState
import com.sotti.roller.coasters.presentation.about.me.model.TopicDescription
import com.sotti.roller.coasters.presentation.about.me.model.TopicDescriptionImage
import com.sotti.roller.coasters.presentation.about.me.model.TopicHyperlink
import com.sotti.roller.coasters.presentation.design.system.colors.color.colors
import com.sotti.roller.coasters.presentation.design.system.colors.color.externalNavigationPrimaryColor
import com.sotti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sotti.roller.coasters.presentation.design.system.hero.image.HeroImage
import com.sotti.roller.coasters.presentation.design.system.images.ui.Image
import com.sotti.roller.coasters.presentation.design.system.text.Text
import com.sotti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sotti.roller.coasters.presentation.format.annotatedLinkString
import com.sotti.roller.coasters.presentation.previews.RollerCoastersTallPreview
import com.sotti.roller.coasters.presentation.utils.Spacer

@Composable
internal fun BottomSheetContent(
    onAction: (AboutMeAction) -> Unit,
    state: TopicDescription,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(horizontal = dimensions.spacing.medium, vertical = dimensions.spacing.large),
        horizontalAlignment = CenterHorizontally,
    ) {
        HeaderImage(state.image)
        Spacer(dimensions.spacing.large)
        Text.Title.Large(textResId = state.titleResId)
        Spacer(dimensions.spacing.medium)
        Text.Body.Medium(textResId = state.bodyResId, modifier = Modifier.fillMaxWidth())
        state.hyperlink?.let {
            Spacer(dimensions.spacing.medium)
            Hyperlink(state.hyperlink, onAction)
        }
    }
}

@Composable
private fun HeaderImage(
    state: TopicDescriptionImage,
) {
    val widthPercent = 0.66f
    when (state) {
        is TopicDescriptionImage.HeroImage -> HeroImage(
            image = state.state,
            modifier = Modifier
                .fillMaxWidth(widthPercent)
                .aspectRatio(1.0f),
        )

        is TopicDescriptionImage.Image -> Image(
            state = state.state,
            modifier = Modifier.fillMaxWidth(widthPercent),
        )
    }
}

@Composable
private fun ColumnScope.Hyperlink(
    hyperlink: TopicHyperlink,
    onAction: (AboutMeAction) -> Unit,
) {
    val annotatedLinkString = annotatedLinkString(
        color = colors.primary,
        text = stringResource(hyperlink.textResId),
        url = stringResource(hyperlink.urlResId),
    )

    val primaryColor = externalNavigationPrimaryColor(hyperlink.urlResId)

    Text.Body.Medium(
        modifier = Modifier
            .align(Alignment.Start)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onAction(OpenUrl(hyperlink.urlResId, primaryColor = primaryColor)) },
        text = annotatedLinkString,
    )
}

@Composable
@RollerCoastersTallPreview
internal fun AboutMeUiBottomSheetContentPreview(
    @PreviewParameter(AboutMeUiBottomSheetContentStateProvider::class)
    previewState: AboutMeBottomSheetPreviewState,
) {
    RollerCoastersTheme {
        Surface {
            BottomSheetContent(
                onAction = previewState.onAction,
                state = previewState.state,
            )
        }
    }
}

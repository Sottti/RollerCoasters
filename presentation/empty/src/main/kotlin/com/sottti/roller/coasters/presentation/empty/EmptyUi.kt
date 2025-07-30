package com.sottti.roller.coasters.presentation.empty

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.illustrations.data.Illustrations
import com.sottti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.informative.InformativeUi
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
public fun EmptyUi(
    modifier: Modifier = Modifier,
    illustration: IllustrationState = Illustrations.DragonKhanAndShambhala.state,
    @StringRes primaryText: Int = R.string.empty_primary_text_default,
    @StringRes secondaryText: Int = R.string.empty_secondary_text_default,
) {
    InformativeUi(
        illustration = illustration,
        primaryText = primaryText,
        secondaryText = secondaryText,
        modifier = modifier,
    )
}

@Composable
@RollerCoastersPreview
internal fun EmptyUiPreview(
    @PreviewParameter(EmptyUiStateProvider::class)
    viewState: EmptyState?,
) {
    RollerCoastersPreviewTheme {
        when (viewState) {
            null -> EmptyUi(modifier = Modifier.background(colors.background))
            else -> EmptyUi(
                modifier = Modifier.background(colors.background),
                illustration = viewState.illustration,
                primaryText = viewState.primaryText,
                secondaryText = viewState.secondaryText,
            )
        }
    }
}
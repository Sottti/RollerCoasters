package com.sottti.roller.coasters.presentation.informative

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.roller.coasters.presentation.design.system.illustrations.ui.CircledIllustration
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import com.sottti.roller.coasters.presentation.utils.Spacer

@Composable
public fun InformativeUi(
    illustration: IllustrationState,
    @StringRes primaryText: Int,
    @StringRes secondaryText: Int,
    modifier: Modifier = Modifier,
    button: InformativeButton? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensions.padding.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(weight = 1f)
        CircledIllustration(
            state = illustration,
            modifier = Modifier.fillMaxWidth(0.8f),
        )
        Spacer(weight = 0.5f)
        Text.Headline.Medium(
            text = stringResource(primaryText),
            textColor = colors.onBackground,
            textAlign = TextAlign.Center,
        )
        Spacer(size = dimensions.padding.small)
        Text.Body.Medium(
            text = stringResource(secondaryText),
            textColor = colors.onBackground,
            textAlign = TextAlign.Center,
        )

        Spacer(weight = 1f)
        button?.let {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = button.onClick
            ) {
                Text.Vanilla(button.text)
            }
        }
    }
}

@Composable
@RollerCoastersPreview
internal fun InformativeUiPreview(
    @PreviewParameter(InformativeUiStateProvider::class)
    viewState: InformativeState,
) {
    RollerCoastersPreviewTheme {
        InformativeUi(
            modifier = Modifier.background(colors.background),
            illustration = viewState.illustration,
            primaryText = viewState.primaryText,
            secondaryText = viewState.secondaryText,
            button = toButton(viewState.buttonText),
        )
    }
}

@Composable
private fun toButton(@StringRes text: Int?): InformativeButton? =
    text?.let {
        InformativeButton(
            text = text,
            onClick = {},
        )
    }
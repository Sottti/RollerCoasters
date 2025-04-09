package com.sottti.roller.coasters.presentation.informative

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.roller.coasters.presentation.design.system.illustrations.data.Illustrations.BrokenTrack
import com.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.roller.coasters.presentation.design.system.illustrations.ui.Illustration
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions

@Composable
public fun InformativeUi(
    illustration: IllustrationState,
    @StringRes primaryText: Int,
    @StringRes secondaryText: Int,
    @StringRes buttonText: Int,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensions.padding.medium)
    ) {
        Illustration(
            state = BrokenTrack.state,
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.25f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            PrimaryText(primaryText)
            Spacer(modifier = Modifier.height(dimensions.padding.medium))
            SecondaryText(secondaryText)
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = modifier
                .padding(dimensions.padding.medium)
                .weight(0.25f),
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                Text.Vanilla(buttonText)
            }
        }
    }
}

@Composable
private fun PrimaryText(primaryText: Int) {
    Text.Display.Small(
        text = stringResource(primaryText),
        textColor = colors.onBackground,
    )
}

@Composable
private fun SecondaryText(secondaryText: Int) {
    Text.Body.Medium(
        text = stringResource(secondaryText),
        textColor = colors.onBackground,
        textAlign = TextAlign.Center,
    )
}
package com.sottti.roller.coasters.presentation.informative

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
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
import com.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.roller.coasters.presentation.design.system.illustrations.ui.Illustration
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.text.Text

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
        Illustration(
            state = illustration,
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
        )

        Text.Headline.Medium(
            text = stringResource(primaryText),
            textColor = colors.onBackground,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(dimensions.padding.medium))
        Text.Body.Medium(
            text = stringResource(secondaryText),
            textColor = colors.onBackground,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.weight(0.25f))
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
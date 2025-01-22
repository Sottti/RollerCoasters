package co.cuvva.presentation.design.system.text

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text as MaterialText

@Composable
public fun Text(
    @StringRes textResId: Int,
) {
    MaterialText(stringResource(textResId))
}
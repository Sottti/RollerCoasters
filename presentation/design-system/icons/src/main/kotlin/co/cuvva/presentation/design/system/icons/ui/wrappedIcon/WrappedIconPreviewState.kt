package co.cuvva.presentation.design.system.icons.ui.wrappedIcon

import androidx.annotation.StringRes
import co.cuvva.presentation.design.system.icons.model.IconState

internal data class WrappedIconPreviewState(
    @StringRes val text: Int,
    val iconState: IconState,
    val onClick: (() -> Unit),
)
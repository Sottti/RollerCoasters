package com.sotti.roller.coasters.presentation.design.system.icons.ui.pilledIcon

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sotti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.design.system.icons.R

internal class PilledIconPreviewProvider : PreviewParameterProvider<PilledIconPreviewState> {
    override val values = buildList {
        add(
            PilledIconPreviewState(
                iconState = Icons.Explore.filled,
                onClick = { },
                text = R.string.pilled_icon_preview_title,

                )
        )
    }.asSequence()
}
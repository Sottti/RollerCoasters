package co.cuvva.presentation.design.system.icons.ui.wrappedIcon

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.design.system.icons.R

internal class WrappedIconPreviewProvider : PreviewParameterProvider<WrappedIconPreviewState> {
    override val values = buildList {
        add(
            WrappedIconPreviewState(
                iconState = Icons.Explore.filled,
                onClick = { },
                text = R.string.wrapped_icon_preview_title,

                )
        )
    }.asSequence()
}
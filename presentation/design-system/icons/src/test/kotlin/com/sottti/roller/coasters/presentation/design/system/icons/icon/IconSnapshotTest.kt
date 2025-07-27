package com.sottti.roller.coasters.presentation.design.system.icons.icon

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.IconPreview
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.IconPreviewProvider
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.IconPreviewState
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class IconSnapshotTest(
    nightMode: NightMode,
    state: IconPreviewState,
) : BasePaparazziSnapshotTest<IconPreviewState>(
    nightMode,
    state,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

    override fun snapshotContent(state: IconPreviewState) {
        IconPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            IconPreviewProvider().paparazziParameters()
    }
}

package com.sottti.roller.coasters.presentation.design.system.progress.indicators

import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ProgressIndicatorSnapshotTest(
    nightMode: NightMode,
    modifier: Modifier,
) : BasePaparazziSnapshotTest<Modifier>(
    nightMode,
    modifier,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

    override fun snapshotContent(state: Modifier) {
        ProgressIndicatorPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            ProgressIndicatorPreviewProvider().paparazziParameters()
    }
}

package com.sottti.roller.coasters.presentation.design.system.dialogs.informative

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class DialogInformativeSnapshotTest(
    nightMode: NightMode,
    state: DialogInformativeState,
) : BasePaparazziSnapshotTest<DialogInformativeState>(
    nightMode,
    state,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

    override fun snapshotContent(state: DialogInformativeState) {
        DialogInformativePreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            DialogInformativePreviewProvider().paparazziParameters()
    }
}

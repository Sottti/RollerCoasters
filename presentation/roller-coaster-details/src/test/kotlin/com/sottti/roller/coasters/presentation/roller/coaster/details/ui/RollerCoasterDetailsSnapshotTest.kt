package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsPreviewState
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class RollerCoasterDetailsSnapshotTest(
    nightMode: NightMode,
    state: RollerCoasterDetailsPreviewState,
) : BasePaparazziSnapshotTest<RollerCoasterDetailsPreviewState>(nightMode, state) {

    override fun snapshotContent(state: RollerCoasterDetailsPreviewState) {
        CompositionLocalProvider(LocalInspectionMode provides true) {
            RollerCoasterDetailsUiPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            RollerCoasterDetailsUiPreviewProvider().paparazziParameters()
    }
}

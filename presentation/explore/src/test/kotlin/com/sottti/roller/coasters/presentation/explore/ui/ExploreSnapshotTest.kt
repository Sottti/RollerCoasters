package com.sottti.roller.coasters.presentation.explore.ui

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.explore.model.ExplorePreviewState
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ExploreSnapshotTest(
    nightMode: NightMode,
    state: ExplorePreviewState,
) : BasePaparazziSnapshotTest<ExplorePreviewState>(nightMode, state) {

    override fun snapshotContent(state: ExplorePreviewState) {
        ExploreUiPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            ExploreUiStateProvider().paparazziParameters()
    }
}

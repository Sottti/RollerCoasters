package com.sottti.roller.coasters.presentation.explore.ui

import com.sottti.roller.coasters.presentation.tests.paparazzi
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.explore.model.ExplorePreviewState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ExploreSnapshotTest(
    nightMode: NightMode,
    private val state: ExplorePreviewState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            ExploreUiPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            nightModeParameters(ExploreUiStateProvider().values)
    }
}

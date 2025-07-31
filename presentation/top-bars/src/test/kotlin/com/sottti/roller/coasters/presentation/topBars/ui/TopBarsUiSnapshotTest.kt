package com.sottti.roller.coasters.presentation.topBars.ui

import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.sottti.roller.coasters.presentation.tests.paparazzi
import com.sottti.roller.coasters.presentation.top.bars.ui.MainTopBarPreview
import com.sottti.roller.coasters.presentation.top.bars.ui.MainTopBarState
import com.sottti.roller.coasters.presentation.top.bars.ui.MainTopBarUiStateProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class TopBarsUiSnapshotTest(
    nightMode: NightMode,
    private val state: MainTopBarState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            MainTopBarPreview(state)
        }
    }

    companion object Companion {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(MainTopBarUiStateProvider().values)
    }
}
package com.sottti.roller.coasters.presentation.design.system.search.box

import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.paparazzi.nightModeParameters
import com.sottti.roller.coasters.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class SearchBoxSnapshotTest(
    nightMode: NightMode,
    private val state: SearchBoxState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            SearchBoxPreview(state)
        }
    }

    companion object Companion {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(SearchBoxStateProvider().values)
    }
}

package com.sottti.roller.coasters.presentation.design.system.search.box

import com.sottti.roller.coasters.presentation.tests.paparazzi
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class SearchBoxSnapshotTest(
    nightMode: NightMode,
    private val state: SearchBoxViewState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            SearchBoxPreview(state)
        }
    }

    companion object Companion {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            nightModeParameters(SearchBoxViewStateProvider().values)
    }
}

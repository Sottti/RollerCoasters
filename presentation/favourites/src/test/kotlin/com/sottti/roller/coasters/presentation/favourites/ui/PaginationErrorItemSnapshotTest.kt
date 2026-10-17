package com.sottti.roller.coasters.presentation.favourites.ui

import com.android.ide.common.rendering.api.SessionParams.RenderingMode.SHRINK
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.paparazzi.nightModeParameters
import com.sottti.roller.coasters.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class PaginationErrorItemSnapshotTest(
    nightMode: NightMode,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, renderingMode = SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            PaginationErrorItemPreview()
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = nightModeParameters()
    }
}

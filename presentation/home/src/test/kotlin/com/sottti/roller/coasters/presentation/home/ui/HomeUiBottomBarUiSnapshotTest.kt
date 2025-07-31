package com.sottti.roller.coasters.presentation.home.ui

import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.sottti.roller.coasters.presentation.tests.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class HomeUiBottomBarUiSnapshotTest(
    nightMode: NightMode,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            BottomBarPreview()
        }
    }

    companion object Companion {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = nightModeParameters()
    }
}
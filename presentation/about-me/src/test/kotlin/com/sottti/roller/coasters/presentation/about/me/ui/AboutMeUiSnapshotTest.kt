package com.sottti.roller.coasters.presentation.about.me.ui

import com.sottti.roller.coasters.presentation.tests.paparazzi
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.about.me.model.AboutMePreviewState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class AboutMeUiSnapshotTest(
    nightMode: NightMode,
    private val state: AboutMePreviewState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            AboutMeUiPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            nightModeParameters(AboutMeUiStateProvider().values)
    }
}

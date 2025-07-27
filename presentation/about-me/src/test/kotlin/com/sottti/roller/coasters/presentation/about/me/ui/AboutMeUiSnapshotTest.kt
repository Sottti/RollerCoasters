package com.sottti.roller.coasters.presentation.about.me.ui

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_6_PRO
import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.about.me.model.AboutMePreviewState
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class AboutMeUiSnapshotTest(
    nightMode: NightMode,
    state: AboutMePreviewState,
) : BasePaparazziSnapshotTest<AboutMePreviewState>(nightMode, state) {

    override fun snapshotContent(state: AboutMePreviewState) {
        AboutMeUiPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            AboutMeUiStateProvider().paparazziParameters()
    }
}

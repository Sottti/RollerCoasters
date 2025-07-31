package com.sottti.roller.coasters.presentation.about.me.ui

import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeBottomSheetPreviewState
import com.sottti.roller.coasters.presentation.about.me.ui.bottomsheets.AboutMeUiBottomSheetContentPreview
import com.sottti.roller.coasters.presentation.about.me.ui.bottomsheets.AboutMeUiBottomSheetContentStateProvider
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.sottti.roller.coasters.presentation.tests.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class AboutMeUiBottomSheetContentSnapshotTest(
    nightMode: NightMode,
    private val state: AboutMeBottomSheetPreviewState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            AboutMeUiBottomSheetContentPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(AboutMeUiBottomSheetContentStateProvider().values)
    }
}
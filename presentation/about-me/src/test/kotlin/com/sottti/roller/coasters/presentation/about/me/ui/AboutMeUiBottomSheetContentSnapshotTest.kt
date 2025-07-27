package com.sottti.roller.coasters.presentation.about.me.ui

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeBottomSheetPreviewState
import com.sottti.roller.coasters.presentation.about.me.ui.bottomsheets.AboutMeUiBottomSheetContentPreview
import com.sottti.roller.coasters.presentation.about.me.ui.bottomsheets.AboutMeUiBottomSheetContentStateProvider
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class AboutMeUiBottomSheetContentSnapshotTest(
    nightMode: NightMode,
    state: AboutMeBottomSheetPreviewState,
) : BasePaparazziSnapshotTest<AboutMeBottomSheetPreviewState>(nightMode, state) {

    override fun snapshotContent(state: AboutMeBottomSheetPreviewState) {
        AboutMeUiBottomSheetContentPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            AboutMeUiBottomSheetContentStateProvider().paparazziParameters()
    }
}

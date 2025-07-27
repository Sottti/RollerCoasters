package com.sottti.roller.coasters.presentation.settings.ui

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.settings.model.SettingsPreviewState
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class SettingsUiSnapshotTest(
    nightMode: NightMode,
    state: SettingsPreviewState,
) : BasePaparazziSnapshotTest<SettingsPreviewState>(nightMode, state) {

    override fun snapshotContent(state: SettingsPreviewState) {
        SettingsUiPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            SettingsUiStateProvider().paparazziParameters()
    }
}

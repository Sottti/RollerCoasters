package com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons

import com.sottti.roller.coasters.presentation.tests.paparazzi
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class DialogWithRadioButtonsSnapshotTest(
    nightMode: NightMode,
    private val state: DialogWithRadioButtonsState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            DialogWithRadioButtonsPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            nightModeParameters(DialogWithRadioButtonsPreviewProvider().values)
    }
}

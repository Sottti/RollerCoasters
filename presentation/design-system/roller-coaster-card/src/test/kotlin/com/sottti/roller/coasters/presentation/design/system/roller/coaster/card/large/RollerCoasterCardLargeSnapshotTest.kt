package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.large

import com.sottti.roller.coasters.presentation.tests.paparazzi
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class RollerCoasterCardLargeSnapshotTest(
    nightMode: NightMode,
    private val state: RollerCoasterCardLargeState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            RollerCoasterCardLargePreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            nightModeParameters(RollerCoasterCardLargePreviewProvider().values)
    }
}

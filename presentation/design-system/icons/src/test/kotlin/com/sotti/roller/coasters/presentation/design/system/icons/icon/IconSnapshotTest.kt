package com.sotti.roller.coasters.presentation.design.system.icons.icon

import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sotti.roller.coasters.presentation.design.system.icons.ui.icon.IconPreview
import com.sotti.roller.coasters.presentation.design.system.icons.ui.icon.IconState
import com.sotti.roller.coasters.presentation.design.system.icons.ui.icon.IconStateProvider
import com.sotti.roller.coasters.presentation.paparazzi.nightModeParameters
import com.sotti.roller.coasters.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class IconSnapshotTest(
    nightMode: NightMode,
    private val state: IconState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            IconPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(IconStateProvider().values)
    }
}

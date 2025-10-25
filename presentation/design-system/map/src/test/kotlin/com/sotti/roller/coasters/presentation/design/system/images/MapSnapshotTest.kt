package com.sotti.roller.coasters.presentation.design.system.images

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sotti.roller.coasters.presentation.design.system.map.MapPreview
import com.sotti.roller.coasters.presentation.design.system.map.MapState
import com.sotti.roller.coasters.presentation.design.system.map.MapStateProvider
import com.sotti.roller.coasters.presentation.paparazzi.nightModeParameters
import com.sotti.roller.coasters.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class MapSnapshotTest(
    nightMode: NightMode,
    private val state: MapState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                MapPreview(state)
            }
        }
    }

    companion object Companion {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(MapStateProvider().values)
    }
}

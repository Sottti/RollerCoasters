package com.sottti.roller.coasters.presentation.design.system.images

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.map.MapPreview
import com.sottti.roller.coasters.presentation.design.system.map.MapState
import com.sottti.roller.coasters.presentation.design.system.map.MapStateProvider
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.sottti.roller.coasters.presentation.tests.paparazzi
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
    val paparazzi = paparazzi(nightMode)

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
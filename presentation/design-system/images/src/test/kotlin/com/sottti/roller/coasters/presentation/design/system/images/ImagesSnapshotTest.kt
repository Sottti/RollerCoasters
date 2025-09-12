package com.sottti.roller.coasters.presentation.design.system.images

import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.images.ui.ImagePreview
import com.sottti.roller.coasters.presentation.design.system.images.ui.ImagePreviewState
import com.sottti.roller.coasters.presentation.design.system.images.ui.ImageStateProvider
import com.sottti.roller.coasters.presentation.paparazzi.nightModeParameters
import com.sottti.roller.coasters.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ImagesSnapshotTest(
    nightMode: NightMode,
    private val state: ImagePreviewState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            ImagePreview(state)
        }
    }

    companion object Companion {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(ImageStateProvider().values)

    }
}
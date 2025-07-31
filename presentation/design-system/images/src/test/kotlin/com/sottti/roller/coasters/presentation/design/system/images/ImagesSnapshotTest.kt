package com.sottti.roller.coasters.presentation.design.system.images

import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.images.ui.ImagePreview
import com.sottti.roller.coasters.presentation.design.system.images.ui.ImagePreviewState
import com.sottti.roller.coasters.presentation.design.system.images.ui.ImageStateProvider
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.sottti.roller.coasters.presentation.tests.paparazzi
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
    val paparazzi = paparazzi(nightMode)

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
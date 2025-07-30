package com.sottti.roller.coasters.presentation.design.system.progress.indicators

import androidx.compose.ui.Modifier
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.sottti.roller.coasters.presentation.tests.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ProgressIndicatorSnapshotTest(
    nightMode: NightMode,
    private val modifier: Modifier,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            ProgressIndicatorPreview(modifier)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(ProgressIndicatorPreviewProvider().values)
    }
}
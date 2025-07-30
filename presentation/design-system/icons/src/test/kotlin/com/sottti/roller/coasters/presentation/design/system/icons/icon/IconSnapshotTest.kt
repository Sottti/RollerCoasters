package com.sottti.roller.coasters.presentation.design.system.icons.icon

import com.sottti.roller.coasters.presentation.tests.paparazzi
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.IconPreview
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.IconPreviewProvider
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.IconPreviewState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class IconSnapshotTest(
    nightMode: NightMode,
    private val state: IconPreviewState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            IconPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            nightModeParameters(IconPreviewProvider().values)
    }
}

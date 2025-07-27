package com.sottti.roller.coasters.presentation.design.system.text

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class TextSnapshotTest(
    nightMode: NightMode,
) : BasePaparazziSnapshotTest<Unit>(
    nightMode,
    Unit,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

    override fun snapshotContent(state: Unit) {
        TextPreview()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            listOf(
                arrayOf(NightMode.NOTNIGHT),
                arrayOf(NightMode.NIGHT),
            )
    }
}

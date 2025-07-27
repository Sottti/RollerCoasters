package com.sottti.roller.coasters.presentation.informative

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class InformativeSnapshotTest(
    nightMode: NightMode,
    state: InformativeState,
) : BasePaparazziSnapshotTest<InformativeState>(nightMode, state) {

    override fun snapshotContent(state: InformativeState) {
        InformativeUiPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            InformativeUiStateProvider().paparazziParameters()
    }
}

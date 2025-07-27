package com.sottti.roller.coasters.presentation.empty

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class EmptySnapshotTest(
    nightMode: NightMode,
    state: EmptyState?,
) : BasePaparazziSnapshotTest<EmptyState?>(nightMode, state) {

    override fun snapshotContent(state: EmptyState?) {
        EmptyUiPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            EmptyUiStateProvider().paparazziParameters()
    }
}

package com.sottti.roller.coasters.presentation.error

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ErrorSnapshotTest(
    nightMode: NightMode,
    state: ErrorState?,
) : BasePaparazziSnapshotTest<ErrorState?>(nightMode, state) {

    override fun snapshotContent(state: ErrorState?) {
        ErrorUiPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            ErrorUiStateProvider().paparazziParameters()
    }
}

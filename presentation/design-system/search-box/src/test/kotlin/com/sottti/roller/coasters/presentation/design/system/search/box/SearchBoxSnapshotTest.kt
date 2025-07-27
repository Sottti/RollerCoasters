package com.sottti.roller.coasters.presentation.design.system.search.box

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class SearchBoxSnapshotTest(
    nightMode: NightMode,
    state: SearchBoxViewState,
) : BasePaparazziSnapshotTest<SearchBoxViewState>(
    nightMode,
    state,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

    override fun snapshotContent(state: SearchBoxViewState) {
        SearchBoxPreview(state)
    }

    companion object Companion {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            SearchBoxViewStateProvider().paparazziParameters()
    }
}

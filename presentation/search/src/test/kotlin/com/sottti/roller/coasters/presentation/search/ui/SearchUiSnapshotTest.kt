package com.sottti.roller.coasters.presentation.search.ui

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.search.model.SearchPreviewState
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class SearchUiSnapshotTest(
    nightMode: NightMode,
    state: SearchPreviewState,
) : BasePaparazziSnapshotTest<SearchPreviewState>(nightMode, state) {

    override fun snapshotContent(state: SearchPreviewState) {
        SearchUiPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            SearchUiStateProvider().paparazziParameters()
    }
}

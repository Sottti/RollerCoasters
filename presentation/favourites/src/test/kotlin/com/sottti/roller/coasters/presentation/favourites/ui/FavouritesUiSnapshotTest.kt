package com.sottti.roller.coasters.presentation.favourites.ui

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesPreviewState
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class FavouritesUiSnapshotTest(
    nightMode: NightMode,
    state: FavouritesPreviewState,
) : BasePaparazziSnapshotTest<FavouritesPreviewState>(nightMode, state) {

    override fun snapshotContent(state: FavouritesPreviewState) {
        FavouritesUiPreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            FavouritesUiStateProvider().paparazziParameters()
    }
}

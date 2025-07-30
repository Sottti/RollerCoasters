package com.sottti.roller.coasters.presentation.favourites.ui

import com.sottti.roller.coasters.presentation.tests.paparazzi
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesPreviewState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class FavouritesUiSnapshotTest(
    nightMode: NightMode,
    private val state: FavouritesPreviewState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            FavouritesUiPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            nightModeParameters(FavouritesUiStateProvider().values)
    }
}

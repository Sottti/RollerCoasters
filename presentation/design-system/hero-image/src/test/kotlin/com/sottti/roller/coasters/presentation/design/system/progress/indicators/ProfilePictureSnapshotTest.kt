package com.sottti.roller.coasters.presentation.design.system.progress.indicators

import com.sottti.roller.coasters.presentation.tests.paparazzi
import com.sottti.roller.coasters.presentation.tests.nightModeParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.hero.image.HeroImagePreview
import com.sottti.roller.coasters.presentation.design.system.hero.image.HeroImageState
import com.sottti.roller.coasters.presentation.design.system.hero.image.ProfilePicturePreviewProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ProfilePictureSnapshotTest(
    nightMode: NightMode,
    private val state: HeroImageState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            HeroImagePreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> =
            nightModeParameters(ProfilePicturePreviewProvider().values)
    }
}

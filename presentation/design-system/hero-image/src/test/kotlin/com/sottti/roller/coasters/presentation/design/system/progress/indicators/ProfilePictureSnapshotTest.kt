package com.sottti.roller.coasters.presentation.design.system.progress.indicators

import com.sottti.roller.coasters.presentation.utils.BasePaparazziSnapshotTest
import com.sottti.roller.coasters.presentation.utils.paparazziParameters
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.roller.coasters.presentation.design.system.hero.image.HeroImagePreview
import com.sottti.roller.coasters.presentation.design.system.hero.image.HeroImageState
import com.sottti.roller.coasters.presentation.design.system.hero.image.ProfilePicturePreviewProvider
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ProfilePictureSnapshotTest(
    nightMode: NightMode,
    state: HeroImageState,
) : BasePaparazziSnapshotTest<HeroImageState>(
    nightMode,
    state,
    renderingMode = SessionParams.RenderingMode.SHRINK,
) {

    override fun snapshotContent(state: HeroImageState) {
        HeroImagePreview(state)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            ProfilePicturePreviewProvider().paparazziParameters()
    }
}

package com.roller.coasters.presentation.design.system.illustrations.data

import co.sottti.roller.coasters.presentation.design.system.illustrations.R
import com.roller.coasters.presentation.design.system.illustrations.model.IllustrationState

public object Illustrations {
    public object BrokenTrack {
        public val Description: Int = R.string.description_illustration_broken_track
        public val state: IllustrationState =
            IllustrationState(
                resId = R.drawable.il_broken_track,
                descriptionResId = Description,
            )
    }
}
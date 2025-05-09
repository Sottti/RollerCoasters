package com.roller.coasters.presentation.design.system.illustrations.data

import com.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.roller.coasters.presentation.design.system.illustrations.R

public object Illustrations {
    public object BrokenTrack {
        public val Description: Int = R.string.description_illustration_broken_track
        public val state: IllustrationState =
            IllustrationState(
                resId = R.drawable.il_broken_track,
                descriptionResId = Description,
            )
    }

    public object EmptyTrack {
        public val Description: Int = R.string.description_illustration_empty_track
        public val state: IllustrationState =
            IllustrationState(
                resId = R.drawable.il_empty_track,
                descriptionResId = Description,
            )
    }
}
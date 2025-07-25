package com.sottti.roller.coasters.presentation.design.system.illustrations.data

import com.sottti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.roller.coasters.presentation.design.system.illustrations.R

public object Illustrations {
    public object BrokenTrack {
        private val description: Int = R.string.description_illustration_broken_track
        public val state: IllustrationState =
            IllustrationState(
                resId = R.drawable.il_broken_track,
                descriptionResId = description,
            )
    }

    public object EmptyTrack {
        private val description: Int = R.string.description_illustration_empty_track
        public val state: IllustrationState =
            IllustrationState(
                resId = R.drawable.il_empty_track,
                descriptionResId = description,
            )
    }
}
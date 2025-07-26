package com.sottti.roller.coasters.presentation.design.system.illustrations.data

import com.sottti.roller.coasters.presentation.design.system.illustrations.R
import com.sottti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState

public object Illustrations {
    public object DragonKhanAndShambhala {
        private val description: Int = R.string.description_illustration_dragon_khan_and_shambhala
        public val state: IllustrationState =
            IllustrationState(
                resId = R.drawable.il_dragon_khan_and_shambhala,
                descriptionResId = description,
            )
    }
}
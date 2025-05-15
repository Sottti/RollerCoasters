package com.roller.coasters.presentation.design.system.images.data

import com.roller.coasters.presentation.design.system.images.model.ImageState
import com.sottti.roller.coasters.presentation.design.system.images.R

public object Images {
    public object ProfilePicture {
        private val description: Int = R.string.description_image_profile_picture
        public val state: ImageState =
            ImageState(
                resId = R.drawable.img_profile_picture,
                descriptionResId = description,
            )
    }
}
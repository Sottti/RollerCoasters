package com.roller.coasters.presentation.design.system.images.data

import com.roller.coasters.presentation.design.system.images.model.ImageState
import com.sottti.roller.coasters.presentation.design.system.images.R

public object Images {
    public object Architecture {
        private val description: Int = R.string.description_image_architecture
        public val state: ImageState =
            ImageState(
                resId = R.drawable.img_architecture,
                descriptionResId = description,
            )
    }

    public object DesignSystems {
        private val description: Int = R.string.description_image_design_systems
        public val state: ImageState =
            ImageState(
                resId = R.drawable.img_design_systems,
                descriptionResId = description,
            )
    }

    public object JetpackComposeLogo {
        private val description: Int = R.string.description_image_logo_jetpack_compose
        public val state: ImageState =
            ImageState(
                resId = R.drawable.img_logo_jetpack_compose,
                descriptionResId = description,
            )
    }

    public object MaterialDesign3Expressive {
        private val description: Int = R.string.description_image_material_design_3_expressive
        public val state: ImageState =
            ImageState(
                resId = R.drawable.img_material_design_3_expressive,
                descriptionResId = description,
            )
    }

    public object ProfilePicture2024 {
        private val description: Int = R.string.description_image_profile_picture_2024
        public val state: ImageState =
            ImageState(
                resId = R.drawable.img_profile_picture_2024,
                descriptionResId = description,
            )
    }
}
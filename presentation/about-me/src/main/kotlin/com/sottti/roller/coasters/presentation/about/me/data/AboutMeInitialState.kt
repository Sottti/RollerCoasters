package com.sottti.roller.coasters.presentation.about.me.data

import com.sottti.roller.coasters.presentation.about.me.R
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfileState
import com.sottti.roller.coasters.presentation.about.me.model.Topic
import com.sottti.roller.coasters.presentation.about.me.model.TopicDescription
import com.sottti.roller.coasters.presentation.about.me.model.TopicDescriptionImage.HeroImage
import com.sottti.roller.coasters.presentation.about.me.model.TopicDescriptionImage.Image
import com.sottti.roller.coasters.presentation.about.me.model.TopicHyperlink
import com.sottti.roller.coasters.presentation.about.me.model.Topics
import com.sottti.roller.coasters.presentation.about.me.model.TopicsState
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.design.system.images.data.Images

internal val initialState = AboutMeState(
    name = R.string.name,
    profileImage = Images.ProfilePicture2024.state,
    socialProfiles = socialProfilesState(),
    title = R.string.name,
    getToKnowMe = getToKnowMeState(),
)

private fun socialProfilesState(): List<SocialProfileState> =
    listOf(
        gitHubProfile(),
        instagramProfile(),
        linkedInProfile(),
        mediumProfile(),
        stackOverflowProfile(),
        xProfile(),
    )

private fun linkedInProfile(): SocialProfileState =
    SocialProfileState(
        icon = Icons.LinkedIn.filled,
        text = R.string.social_profiles_linked_in_text,
        url = R.string.social_profiles_linked_in_url,
    )

private fun xProfile(): SocialProfileState =
    SocialProfileState(
        icon = Icons.X.filled,
        text = R.string.social_profiles_x_text,
        url = R.string.social_profiles_x_url,
    )

private fun gitHubProfile(): SocialProfileState =
    SocialProfileState(
        icon = Icons.GitHub.filled,
        text = R.string.social_profiles_github_text,
        url = R.string.social_profiles_github_url,
    )

private fun stackOverflowProfile(): SocialProfileState =
    SocialProfileState(
        icon = Icons.StackOverflow.filled,
        text = R.string.social_profiles_stack_overflow_text,
        url = R.string.social_profiles_stack_overflow_url,
    )

private fun instagramProfile(): SocialProfileState =
    SocialProfileState(
        icon = Icons.Instagram.filled,
        text = R.string.social_profiles_instagram_text,
        url = R.string.social_profiles_instagram_url,
    )

private fun mediumProfile(): SocialProfileState =
    SocialProfileState(
        icon = Icons.Medium.filled,
        text = R.string.social_profiles_medium_text,
        url = R.string.social_profiles_medium_url,
    )

private fun getToKnowMeState(): TopicsState = TopicsState(
    android = android(),
    hobbies = hobbies(),
    journey = journey(),
    languages = languages(),
)

private fun android(): Topics = Topics(
    firstTopic = Topic(
        textResId = R.string.topic_android_jetpack_compose_title,
        description = TopicDescription(
            image = Image(Images.JetpackComposeLogo.state),
            titleResId = R.string.topic_android_jetpack_compose_title,
            bodyResId = R.string.topic_android_jetpack_compose_description,
            hyperlink = TopicHyperlink(
                textResId = R.string.topic_android_jetpack_compose_hyperlink_text,
                urlResId = R.string.topic_android_jetpack_compose_hyperlink_url,
            ),
        ),
    ),
    secondTopic = Topic(
        textResId = R.string.topic_android_material_design_title,
        description = TopicDescription(
            image = Image(Images.MaterialDesign3Expressive.state),
            titleResId = R.string.topic_android_material_design_title,
            bodyResId = R.string.topic_android_material_design_description,
        ),
    ),
    thirdTopic = Topic(
        textResId = R.string.topic_android_design_systems_title,
        description = TopicDescription(
            image = Image(Images.DesignSystems.state),
            titleResId = R.string.topic_android_design_systems_title,
            bodyResId = R.string.topic_android_design_systems_description,
            hyperlink = TopicHyperlink(
                textResId = R.string.topic_android_design_systems_hyperlink_text,
                urlResId = R.string.topic_android_design_systems_hyperlink_url,
            )
        ),
    ),
    fourthTopic = Topic(
        textResId = R.string.topic_android_architecture_title,
        description = TopicDescription(
            image = Image(Images.AndroidArchitecture.state),
            titleResId = R.string.topic_android_architecture_title,
            bodyResId = R.string.topic_android_architecture_description,
        ),
    ),
)

private fun hobbies(): Topics = Topics(
    firstTopic = Topic(
        textResId = R.string.topic_hobbies_aggressive_inline_title,
        description = TopicDescription(
            image = HeroImage(Images.AggressiveInline.state),
            titleResId = R.string.topic_hobbies_aggressive_inline_title,
            bodyResId = R.string.topic_hobbies_aggressive_inline_description,
        ),
    ),
    secondTopic = Topic(
        textResId = R.string.topic_hobbies_bodyboard_title,
        description = TopicDescription(
            image = HeroImage(Images.Bodyboard.state),
            titleResId = R.string.topic_hobbies_bodyboard_title,
            bodyResId = R.string.topic_hobbies_bodyboard_description,
        ),
    ),
    thirdTopic = Topic(
        textResId = R.string.topic_hobbies_guitar_title,
        description = TopicDescription(
            image = HeroImage(Images.Guitar.state),
            titleResId = R.string.topic_hobbies_guitar_title,
            bodyResId = R.string.topic_hobbies_guitar_description,
        ),
    ),
    fourthTopic = Topic(
        textResId = R.string.topic_hobbies_travel_title,
        description = TopicDescription(
            image = HeroImage(Images.TajMahal.state),
            titleResId = R.string.topic_hobbies_travel_title,
            bodyResId = R.string.topic_hobbies_travel_description,
        ),
    ),
)

private fun languages(): Topics = Topics(
    firstTopic = Topic(
        textResId = R.string.topic_languages_english_title,
        description = TopicDescription(
            image = HeroImage(Images.BigBen.state),
            titleResId = R.string.topic_languages_english_title,
            bodyResId = R.string.topic_languages_english_description,
        ),
    ),
    secondTopic = Topic(
        textResId = R.string.topic_languages_galician_title,
        description = TopicDescription(
            image = HeroImage(Images.CiesIslands.state),
            titleResId = R.string.topic_languages_galician_title,
            bodyResId = R.string.topic_languages_galician_description,
        ),
    ),
    thirdTopic = Topic(
        textResId = R.string.topic_languages_portuguese_title,
        description = TopicDescription(
            image = HeroImage(Images.ValencaDoMinho.state),
            titleResId = R.string.topic_languages_portuguese_title,
            bodyResId = R.string.topic_languages_portuguese_description,
        ),
    ),
    fourthTopic = Topic(
        textResId = R.string.topic_languages_spanish_title,
        description = TopicDescription(
            image = HeroImage(Images.Madrid.state),
            titleResId = R.string.topic_languages_spanish_title,
            bodyResId = R.string.topic_languages_spanish_description,
        ),
    ),
)

private fun journey() = Topic(
    textResId = R.string.topic_journey_title,
    description = TopicDescription(
        image = HeroImage(Images.AndroidDevSummitCredentials.state),
        titleResId = R.string.topic_journey_title,
        bodyResId = R.string.topic_journey_description,
    ),
)

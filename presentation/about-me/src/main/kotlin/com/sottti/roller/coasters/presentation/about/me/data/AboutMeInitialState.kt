package com.sottti.roller.coasters.presentation.about.me.data

import co.cuvva.presentation.design.system.icons.data.Icons
import com.roller.coasters.presentation.design.system.images.data.Images
import com.sottti.roller.coasters.presentation.about.me.R
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.about.me.model.ProfileImageState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfileState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfilesState
import com.sottti.roller.coasters.presentation.about.me.model.Topic
import com.sottti.roller.coasters.presentation.about.me.model.TopicDescription
import com.sottti.roller.coasters.presentation.about.me.model.TopicDescriptionImage.HeroImage
import com.sottti.roller.coasters.presentation.about.me.model.TopicDescriptionImage.Image
import com.sottti.roller.coasters.presentation.about.me.model.TopicHyperlink
import com.sottti.roller.coasters.presentation.about.me.model.Topics
import com.sottti.roller.coasters.presentation.about.me.model.TopicsState

internal val initialState = AboutMeState(
    title = R.string.name,
    name = R.string.name,
    profileImage = ProfileImageState(image = Images.ProfilePicture2024.state),
    socialProfiles = socialProfilesState(),
    topics = topicsState()
)

private fun socialProfilesState(): SocialProfilesState = SocialProfilesState(
    profiles = listOf(
        gitHubProfile(),
        instagramProfile(),
        linkedInProfile(),
        mediumProfile(),
        stackOverflowProfile(),
        xProfile(),
    )
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

private fun topicsState(): TopicsState = TopicsState(
    android = androidTopics(),
    hobbies = hobbiesTopics(),
    languages = languagesTopics(),
)

private fun androidTopics(): Topics = Topics(
    firstTopic = Topic(
        R.string.topic_android_jetpack_compose_title,
        TopicDescription(
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
        R.string.topic_android_material_design_title,
        TopicDescription(
            image = Image(Images.MaterialDesign3Expressive.state),
            titleResId = R.string.topic_android_material_design_title,
            bodyResId = R.string.topic_android_material_design_description,
        ),
    ),
    thirdTopic = Topic(
        R.string.topic_android_design_systems_title,
        TopicDescription(
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
        R.string.topic_android_architecture_title,
        TopicDescription(
            image = Image(Images.AndroidArchitecture.state),
            titleResId = R.string.topic_android_architecture_title,
            bodyResId = R.string.topic_android_architecture_description,
        ),
    ),
)

private fun hobbiesTopics(): Topics = Topics(
    firstTopic = Topic(
        R.string.topic_hobbies_aggressive_inline_title,
        TopicDescription(
            image = HeroImage(Images.AggressiveInline.state),
            titleResId = R.string.topic_hobbies_aggressive_inline_title,
            bodyResId = R.string.topic_hobbies_aggressive_inline_description,
        ),
    ),
    secondTopic = Topic(
        R.string.topic_hobbies_travel_title,
        TopicDescription(
            image = HeroImage(Images.TajMahal.state),
            titleResId = R.string.topic_hobbies_travel_title,
            bodyResId = R.string.topic_hobbies_travel_description,
        ),
    ),
    thirdTopic = Topic(
        R.string.topic_hobbies_guitar_title,
        TopicDescription(
            image = HeroImage(Images.Guitar.state),
            titleResId = R.string.topic_hobbies_guitar_title,
            bodyResId = R.string.topic_hobbies_guitar_description,
        ),
    ),
    fourthTopic = Topic(
        R.string.topic_hobbies_bodyboard_title,
        TopicDescription(
            image = HeroImage(Images.Bodyboard.state),
            titleResId = R.string.topic_hobbies_bodyboard_title,
            bodyResId = R.string.topic_hobbies_bodyboard_description,
        ),
    ),
)

private fun languagesTopics(): Topics = Topics(
    firstTopic = Topic(
        R.string.topic_languages_english_title,
        TopicDescription(
            image = HeroImage(Images.BigBen.state),
            titleResId = R.string.topic_languages_english_title,
            bodyResId = R.string.topic_languages_english_description,
        ),
    ),
    secondTopic = Topic(
        R.string.topic_languages_galician_title,
        TopicDescription(
            image = HeroImage(Images.CiesIslands.state),
            titleResId = R.string.topic_languages_galician_title,
            bodyResId = R.string.topic_languages_galician_description,
        ),
    ),
    thirdTopic = Topic(
        R.string.topic_languages_portuguese_title,
        TopicDescription(
            image = HeroImage(Images.ValencaDoMinho.state),
            titleResId = R.string.topic_languages_portuguese_title,
            bodyResId = R.string.topic_languages_portuguese_description,
        ),
    ),
    fourthTopic = Topic(
        R.string.topic_languages_spanish_title,
        TopicDescription(
            image = HeroImage(Images.Madrid.state),
            titleResId = R.string.topic_languages_spanish_title,
            bodyResId = R.string.topic_languages_spanish_description,
        ),
    ),
)
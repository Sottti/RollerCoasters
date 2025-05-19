package com.sottti.roller.coasters.presentation.about.me.data

import co.cuvva.presentation.design.system.icons.data.Icons
import com.roller.coasters.presentation.design.system.images.data.Images
import com.sottti.roller.coasters.presentation.about.me.R
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.about.me.model.GridTopics
import com.sottti.roller.coasters.presentation.about.me.model.ProfileImageState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfileState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfilesState
import com.sottti.roller.coasters.presentation.about.me.model.TopicsState

internal val initialState = AboutMeState(
    name = R.string.name,
    profileImage = ProfileImageState(image = Images.ProfilePicture2024.state),
    socialProfiles = socialProfilesState(),
    topics = topicsState()
)

private fun topicsState(): TopicsState = TopicsState(
    android = GridTopics(
        firstTopic = R.string.topic_android_jetpack_compose,
        secondTopic = R.string.topic_android_material_design,
        thirdTopic = R.string.topic_android_modular_architecture,
        fourthTopic = R.string.topic_android_software_architecture,
    ),
    hobbies = GridTopics(
        firstTopic = R.string.topic_hobbies_rollerblading,
        fourthTopic = R.string.topic_hobbies_bodyboard,
        secondTopic = R.string.topic_hobbies_travel,
        thirdTopic = R.string.topic_hobbies_guitar,
    ),
    languages = GridTopics(
        firstTopic = R.string.topic_languages_english,
        fourthTopic = R.string.topic_languages_spanish,
        secondTopic = R.string.topic_languages_galician,
        thirdTopic = R.string.topic_languages_portuguese,
    ),
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
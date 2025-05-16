package com.sottti.roller.coasters.presentation.about.me.data

import co.cuvva.presentation.design.system.icons.data.Icons
import com.roller.coasters.presentation.design.system.images.data.Images
import com.sottti.roller.coasters.presentation.about.me.R
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.about.me.model.ProfileImageState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfileState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfilesState

internal val initialState = AboutMeState(
    profileImage = ProfileImageState(
        image = Images.ProfilePicture.state,
    ),
    socialProfiles = SocialProfilesState(
        title = R.string.social_profiles_title,
        profiles = listOf(
            gitHubProfile(),
            instagramProfile(),
            linkedInProfile(),
            stackOverflowProfile(),
            xProfile(),
        )
    ),
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

private fun instagramProfile() : SocialProfileState =
    SocialProfileState(
        icon = Icons.Instagram.filled,
        text = R.string.social_profiles_instagram_text,
        url = R.string.social_profiles_instagram_url,
    )
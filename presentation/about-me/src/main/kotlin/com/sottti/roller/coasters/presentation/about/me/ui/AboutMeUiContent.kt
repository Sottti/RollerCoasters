package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.cuvva.presentation.design.system.icons.ui.wrappedIcon.WrappedIcon
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.roller.coasters.presentation.design.system.images.ui.Image
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.about.me.model.ProfileImageState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfilesState
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions

@Composable
internal fun AboutMeUiContent(
    paddingValues: PaddingValues,
    state: AboutMeState,
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensions.padding.large)
    ) {
        item { ProfileImage(state.profileImage) }
        item { SocialProfiles(state.socialProfiles) }
    }
}


@Composable
internal fun ProfileImage(state: ProfileImageState) {
    Image(
        modifier = Modifier
            .padding(top = dimensions.padding.large)
            .fillMaxWidth(0.5f)
            .aspectRatio(1f),
        state = state.image,
    )
}

@Composable
internal fun SocialProfiles(
    state: SocialProfilesState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.padding.medium),
        verticalArrangement = Arrangement.spacedBy(dimensions.padding.medium)
    ) {
        Text.Title.Large(
            textResId = state.title,
            textColor = colors.onBackground,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            state.profiles.forEach { profile ->
                WrappedIcon(
                    text = profile.text,
                    iconState = profile.icon,
                    onClick = {},
                )
            }
        }
    }
}
package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import co.cuvva.presentation.design.system.icons.ui.wrappedIcon.WrappedIcon
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.roller.coasters.presentation.design.system.images.ui.Image
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction.OpenUrl
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.about.me.model.ProfileImageState
import com.sottti.roller.coasters.presentation.about.me.model.SocialProfilesState
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions

@Composable
internal fun AboutMeUiContent(
    nestedScrollConnection: NestedScrollConnection,
    onAction: (AboutMeAction) -> Unit,
    paddingValues: PaddingValues,
    state: AboutMeState,
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .nestedScroll(nestedScrollConnection),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item { ProfileImage(state.profileImage) }
        item { Spacer(modifier = Modifier.size(dimensions.padding.medium)) }
        item { Name(state.name) }
        item { Spacer(modifier = Modifier.size(dimensions.padding.large)) }
        item { SocialProfiles(onAction = onAction, state = state.socialProfiles) }
        item { Spacer(modifier = Modifier.size(dimensions.padding.large)) }
        item { CardX() }
    }
}


@Composable
internal fun ProfileImage(state: ProfileImageState) {
    Image(
        modifier = Modifier
            .padding(top = dimensions.padding.large)
            .fillMaxWidth(0.33f)
            .aspectRatio(1f),
        state = state.image,
    )
}

@Composable
private fun Name(state: Int) {
    Text.Display.Small(
        textResId = state,
        textColor = colors.onBackground,
    )
}

@Composable
internal fun SocialProfiles(
    onAction: (AboutMeAction) -> Unit,
    state: SocialProfilesState,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensions.padding.medium)
    ) {
        Text.Title.Large(
            modifier = Modifier.padding(horizontal = dimensions.padding.medium),
            textResId = state.title,
            textColor = colors.onBackground,
        )
        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(horizontal = dimensions.padding.medium),
            horizontalArrangement = Arrangement.spacedBy(dimensions.padding.smallMedium),
        ) {
            state.profiles.forEach { profile ->
                WrappedIcon(
                    text = profile.text,
                    iconState = profile.icon,
                    onClick = { onAction(OpenUrl(profile.url)) },
                )
            }
        }
    }
}

@Composable
private fun CardX() {
    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .height(600.dp)
            .fillMaxWidth(),
    ) { }
}
package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterIdentityViewState

@Composable
internal fun RollerCoasterDetails(
    rollerCoaster: RollerCoasterDetailsViewState,
    paddingValues: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = dimensions.padding.medium)
    ) {
        item { RollerCoasterIdentity(rollerCoaster.identity) }
    }
}

@Composable
internal fun RollerCoasterIdentity(
    state: RollerCoasterIdentityViewState,
) {
    Column {
        Header(state.header)
        IdentityTable(state)
    }
}

@Composable
internal fun Header(
    @StringRes text: Int,
) {
    Text.Label.Medium(
        text = stringResource(text),
        textColor = colors.onBackground
    )
}

@Composable
internal fun IdentityTable(
    state: RollerCoasterIdentityViewState,
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(dimensions.padding.medium)) {
            Text.Body.Medium(text = state.name)
        }
    }
}
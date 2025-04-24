package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsRow
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterIdentityViewState
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterStatusViewState
import androidx.compose.material3.ListItem as ListItemMaterial

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
        item { RollerCoasterStatus(rollerCoaster.status) }
    }
}

@Composable
internal fun RollerCoasterIdentity(
    state: RollerCoasterIdentityViewState,
) {
    Column {
        Header(state.header)
        Spacer(modifier = Modifier.padding(dimensions.padding.small))
        IdentityTable(state)
    }
}

@Composable
internal fun RollerCoasterStatus(
    state: RollerCoasterStatusViewState,
) {
    Column {
        Header(state.header)
        Spacer(modifier = Modifier.padding(dimensions.padding.small))
        StatusTable(state)
    }
}

@Composable
internal fun Header(
    @StringRes text: Int,
) {
    Text.Label.Large(
        text = stringResource(text),
        textColor = colors.onBackground
    )
}

@Composable
internal fun IdentityTable(
    state: RollerCoasterIdentityViewState,
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            ListItem(state.name)
            state.formerNames?.let { ListItem(state.formerNames) }
        }
    }
}

@Composable
internal fun StatusTable(
    state: RollerCoasterStatusViewState,
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            ListItem(state.current)
            state.former?.let { formerStatus -> ListItem(formerStatus) }
            state.openedDate?.let { openedDate -> ListItem(openedDate) }
            state.closedDate?.let { closedDate -> ListItem(closedDate) }
        }
    }
}

@Composable
private fun ListItem(
    state: RollerCoasterDetailsRow,
) {
    ListItemMaterial(
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        headlineContent = { Text.Vanilla(text = state.headline) },
        overlineContent = { Text.Vanilla(text = stringResource(state.overline)) },
        trailingContent = { state.trailing?.let { trailing -> Text.Vanilla(text = trailing) } },
    )
}
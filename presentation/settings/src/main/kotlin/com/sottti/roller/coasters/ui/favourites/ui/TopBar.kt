package com.sottti.roller.coasters.ui.favourites.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import co.cuvva.presentation.design.system.icons.Icon
import co.cuvva.presentation.design.system.icons.Icons
import com.sottti.roller.coasters.settings.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.title)) },
        navigationIcon = {
            Icon(
                contentDescriptionResId = Icons.ArrowBack.descriptionResId,
                iconResId = Icons.ArrowBack.rounded,
                onClick = { navController.popBackStack() },
            )
        }
    )
}
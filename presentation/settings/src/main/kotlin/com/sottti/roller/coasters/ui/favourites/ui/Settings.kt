package com.sottti.roller.coasters.ui.favourites.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import co.cuvva.presentation.design.system.icons.Icons
import com.sottti.roller.coasters.settings.R
import com.sottti.roller.coasters.ui.favourites.data.SettingsViewModel

@Composable
fun Settings(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    Scaffold(
        topBar = { TopBar(navController) }
    ) { paddingValues ->
        SettingsContent(paddingValues)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.title)) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = Icons.ArrowBack.filled),
                    contentDescription = stringResource(Icons.ArrowBack.descriptionResId)
                )
            }
        }
    )
}

@Composable
fun SettingsContent(paddingValues: PaddingValues) {

}
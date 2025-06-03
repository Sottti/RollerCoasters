package com.sottti.roller.coasters.presentation.top.bars

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.text.Text

@Composable
@OptIn(ExperimentalMaterial3Api::class)
public fun MainTopBar(
    onNavigateToSettings: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    showTitle: Boolean = false,
    titleResId: Int? = null,
) {
    val colors = when (scrollBehavior) {
        null -> TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        else -> TopAppBarDefaults.topAppBarColors()
    }

    TopAppBar(
        actions = { Icon(onNavigateToSettings) },
        colors = colors,
        scrollBehavior = scrollBehavior,
        title = { Title(titleResId, showTitle) },
    )
}

@Composable
private fun Title(
    @StringRes titleResId: Int?,
    showTitle: Boolean,
) {
    titleResId ?: return
    val title = stringResource(titleResId)
    AnimatedVisibility(
        visible = title.isNotEmpty() && showTitle,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
    ) {
        Text.Vanilla(text = title)
    }
}

@Composable
private fun Icon(onNavigateToSettings: () -> Unit) {
    Icon(
        iconState = Icons.Settings.outlined,
        onClick = { onNavigateToSettings() },
    )
}
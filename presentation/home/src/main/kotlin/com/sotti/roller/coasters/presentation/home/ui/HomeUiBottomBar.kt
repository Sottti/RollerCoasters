package com.sotti.roller.coasters.presentation.home.ui

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.sotti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sotti.roller.coasters.presentation.design.system.text.Text
import com.sotti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sotti.roller.coasters.presentation.home.data.navigationBarItems
import com.sotti.roller.coasters.presentation.home.model.HomeNavigationBarItemState
import com.sotti.roller.coasters.presentation.home.model.HomeNavigationBarState
import com.sotti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
internal fun BottomBar(
    navigationBarItems: HomeNavigationBarState,
    onNavigationBarItemClick: (item: HomeNavigationBarItemState) -> Unit,
) {
    NavigationBar {
        navigationBarItems.items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon) },
                label = { Text.Vanilla(item.labelResId) },
                selected = navigationBarItems.selectedItem == item.destination,
                onClick = { onNavigationBarItemClick(item) },
            )
        }
    }
}

@Composable
@RollerCoastersPreview
internal fun BottomBarPreview() {
    RollerCoastersTheme {
        BottomBar(
            navigationBarItems = navigationBarItems(),
            onNavigationBarItemClick = {},
        )
    }
}

package com.sottti.roller.coasters.presentation.home.ui

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sottti.roller.coasters.presentation.home.data.navigationBarItems
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItemState
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarState
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview

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

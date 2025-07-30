package com.sottti.roller.coasters.presentation.home.ui

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.home.data.navigationBarItems
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItem
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItems
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
internal fun BottomBar(
    navigationBarItems: HomeNavigationBarItems,
    onNavigationBarItemClick: (item: HomeNavigationBarItem) -> Unit,
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
    RollerCoastersPreviewTheme {
        BottomBar(
            navigationBarItems = navigationBarItems(),
            onNavigationBarItemClick = {},
        )
    }
}
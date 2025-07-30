package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.search.box.SearchBox
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchState
import com.sottti.roller.coasters.presentation.top.bars.MainTopBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchTopBar(
    lazyListState: LazyListState,
    onAction: (SearchAction) -> Unit,
    onNavigateToSettings: () -> Unit,
    state: SearchState,
) {
    val containerColor = TopAppBarDefaults.topAppBarColors().containerColor
    val scrolledContainerColor = TopAppBarDefaults.topAppBarColors().scrolledContainerColor
    val isScrolled by remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset > 0 } }
    val backgroundColor by animateColorAsState(
        targetValue = if (isScrolled) scrolledContainerColor else containerColor,
        label = "expandable top bar background color animation",
    )
    Column(modifier = Modifier.background(backgroundColor)) {
        MainTopBar(onNavigateToSettings = onNavigateToSettings)
        SearchBox(
            hint = state.searchBar.hint,
            loading = state.loading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.padding.medium)
                .padding(bottom = dimensions.padding.medium),
            onQueryChange = { onAction(SearchAction.QueryChanged(it)) },
            query = state.searchBar.query,
        )
    }
}

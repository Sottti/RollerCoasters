package com.sottti.roller.coasters.presentation.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
public fun OnScrollToTopUiEffects(
    lazyListState: LazyListState,
    onScrollToTop: (() -> Unit) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val coroutineScope = rememberCoroutineScope()
    val rememberedOnScrollToTop = rememberUpdatedState(onScrollToTop)
    LaunchedEffect(Unit) {
        rememberedOnScrollToTop.value {
            coroutineScope.launch {
                lazyListState.animateScrollToItem(0)
                scrollBehavior.state.contentOffset = 0f
            }
        }
    }
}
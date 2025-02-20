package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.presentation.explore.data.ExploreViewModel

@Composable
public fun ExploreUi(
    nestedScrollConnection: NestedScrollConnection,
) {
    ExploreUiInternal(nestedScrollConnection = nestedScrollConnection)
}

@Composable
private fun ExploreUiInternal(
    nestedScrollConnection: NestedScrollConnection,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val rollerCoasters: LazyPagingItems<RollerCoaster> =
        viewModel
            .rollerCoasters
            .collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(rollerCoasters.itemCount) { index ->
            rollerCoasters[index]?.let { coaster ->
                RollerCoasterItem(coaster)
            }
        }

        rollerCoasters.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = rollerCoasters.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage ?: "An error occurred",
                            modifier = Modifier.fillParentMaxSize()
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = rollerCoasters.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RollerCoasterItem(coaster: RollerCoaster) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = coaster.name.current.value,
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun LoadingView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
private fun ErrorItem(message: String, modifier: Modifier = Modifier) {
    Text(
        text = message,
        modifier = modifier.padding(16.dp),
        color = androidx.compose.ui.graphics.Color.Red
    )
}
package com.sottti.roller.coasters.presentation.design.system.search.box

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicatorSize
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
public fun SearchBox(
    @StringRes hint: Int,
    query: String?,
    loading: Boolean,
    modifier: Modifier = Modifier,
    onQueryChange: (String?) -> Unit,
) {
    val shape = MaterialTheme.shapes.large
    val queryString = query.orEmpty()
    TextField(
        value = queryString,
        onValueChange = onQueryChange,
        modifier = modifier.clip(shape),
        placeholder = { Text.Vanilla(stringResource(hint)) },
        singleLine = true,
        shape = shape,
        leadingIcon = { Icon(iconState = Icons.Search.outlined) },
        trailingIcon = { SearchBoxTrailingIcon(loading, onQueryChange, queryString) },
        colors = searchBoxColors()
    )
}

@Composable
private fun searchBoxColors() = TextFieldDefaults.colors(
    unfocusedIndicatorColor = Color.Transparent,
    focusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
)

@Composable
private fun SearchBoxTrailingIcon(
    loading: Boolean,
    onQueryChange: (String?) -> Unit,
    queryString: String,
) {
    when {
        loading -> ProgressIndicator(size = ProgressIndicatorSize.Small)
        queryString.isNotBlank() -> Icon(
            iconState = Icons.Cancel.outlined,
            onClick = { onQueryChange(null) }
        )
    }
}

@Composable
@RollerCoastersPreview
internal fun SearchBoxPreview(
    @PreviewParameter(SearchBoxViewStateProvider::class)
    state: SearchBoxViewState,
) {
    RollerCoastersPreviewTheme {
        SearchBox(
            hint = state.hint,
            loading = state.loading,
            modifier = Modifier.fillMaxWidth(),
            onQueryChange = state.onQueryChange,
            query = state.query,
        )
    }
}

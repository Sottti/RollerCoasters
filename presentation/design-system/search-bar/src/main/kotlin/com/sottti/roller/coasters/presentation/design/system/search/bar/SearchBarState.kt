package com.sottti.roller.coasters.presentation.design.system.search.bar

import androidx.annotation.StringRes

internal data class SearchBarState(
    @StringRes val hint: Int,
    val query: String?,
    val loading: Boolean,
    val onQueryChange: (String?) -> Unit,
)

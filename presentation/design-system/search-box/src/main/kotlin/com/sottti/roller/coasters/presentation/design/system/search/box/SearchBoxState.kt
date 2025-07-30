package com.sottti.roller.coasters.presentation.design.system.search.box

import androidx.annotation.StringRes

internal data class SearchBoxState(
    @StringRes val hint: Int,
    val query: String?,
    val loading: Boolean,
    val onQueryChange: (String?) -> Unit,
)

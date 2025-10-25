package com.sotti.roller.coasters.presentation.search.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface SearchAction {
    @Immutable
    data class QueryChanged(val query: String?) : SearchAction
}

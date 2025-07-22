package com.sottti.roller.coasters.presentation.search.model

internal sealed interface SearchAction {
    data class QueryChanged(val query: String) : SearchAction
    data object ClearQuery : SearchAction
}
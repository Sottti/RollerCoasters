package com.sottti.roller.coasters.domain.model

public data object NotFound : Exception() {
    private fun readResolve(): Any = NotFound
}
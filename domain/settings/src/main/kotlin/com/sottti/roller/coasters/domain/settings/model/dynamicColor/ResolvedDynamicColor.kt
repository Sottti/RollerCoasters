package com.sottti.roller.coasters.domain.settings.model.dynamicColor

import androidx.annotation.ChecksSdkIntAtLeast

@JvmInline
public value class ResolvedDynamicColor(
    private val isEnabled: Boolean
) {
    @ChecksSdkIntAtLeast(api = 31)
    public fun enabled(): Boolean = isEnabled
}
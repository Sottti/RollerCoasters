package com.sottti.roller.coasters.domain.settings.model.dynamicColor

import androidx.annotation.ChecksSdkIntAtLeast

public data class ResolvedDynamicColor(
    @ChecksSdkIntAtLeast(api = 31) val enabled: Boolean,
)
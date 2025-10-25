package com.sotti.roller.coasters.data.roller.coasters.datasources.remote.serializers

import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.api.API_FIELD_UNDEFINED

internal fun String?.isUndefined(): Boolean =
    this == API_FIELD_UNDEFINED || isNullOrBlank()

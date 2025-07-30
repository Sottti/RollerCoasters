package com.sottti.roller.coasters.presentation.search.model

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster

internal fun RollerCoaster.toState(): SearchResultState =
    SearchResultState(
        id = id.value,
        imageUrl = pictures.main?.url,
        name = name.current.value,
        parkName = park.name.value,
    )

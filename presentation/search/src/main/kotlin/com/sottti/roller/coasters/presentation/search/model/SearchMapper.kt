package com.sottti.roller.coasters.presentation.search.model

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster

internal fun RollerCoaster.toViewState(): SearchResultViewState =
    SearchResultViewState(
        id = id.value,
        imageUrl = pictures.main?.url,
        name = name.current.value,
        parkName = park.name.value,
    )

package com.sottti.roller.coasters.presentation.explore.data

import androidx.paging.PagingData
import androidx.paging.map
import com.sottti.roller.coasters.domain.model.MultiTrackRide
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.SingleTrackRide
import com.sottti.roller.coasters.presentation.explore.model.RollerCoasterUiModel
import com.sottti.roller.coasters.presentation.utils.toDisplayFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun Flow<PagingData<RollerCoaster>>.toUiModel(): Flow<PagingData<RollerCoasterUiModel>> =
    map { pagingData ->
        var indexCounter = 1
        pagingData.map { rollerCoaster -> rollerCoaster.toUiModel(indexCounter++) }
    }

private fun RollerCoaster.toUiModel(ranking: Int) =
    RollerCoasterUiModel(
        imageUrl = pictures.main?.url,
        parkName = park.name.value,
        rollerCoasterName = name.current.value,
        stat = stat(),
        statDetail = "Ranked #$ranking",
    )

private fun RollerCoaster.stat(): String? = when (val ride = specs.ride) {
    is MultiTrackRide -> ride.height?.maxOfOrNull { it.meters.value.toDisplayFormat() } + " meters"
    is SingleTrackRide -> ride.height?.meters?.value?.toDisplayFormat() + " meters"

    null -> null
}
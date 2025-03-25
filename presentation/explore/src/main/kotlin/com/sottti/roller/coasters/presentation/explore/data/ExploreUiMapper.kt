package com.sottti.roller.coasters.presentation.explore.data

import androidx.paging.PagingData
import androidx.paging.map
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun Flow<PagingData<RollerCoaster>>.toUiModel(
    sortByFilter: SortByFilter,
): Flow<PagingData<ExploreRollerCoaster>> =
    map { pagingData ->
        var currentRank = 1
        var previousStat: String? = null
        var itemsWithSameStat = 0

        pagingData.map { rollerCoaster ->
            val currentStat = rollerCoaster.contextualStat(sortByFilter)

            when {
                currentStat != null && currentStat == previousStat -> itemsWithSameStat++
                else -> {
                    currentRank += itemsWithSameStat
                    itemsWithSameStat = 1
                }
            }

            previousStat = currentStat

            rollerCoaster.toUiModel(currentRank, sortByFilter)
        }
    }

private fun RollerCoaster.toUiModel(
    ranking: Int,
    sortByFilter: SortByFilter,
) =
    ExploreRollerCoaster(
        imageUrl = pictures.main?.url,
        parkName = park.name.value,
        rollerCoasterName = name.current.value,
        stat = contextualStat(sortByFilter),
        statDetail = "Ranked #$ranking",
    )

private fun RollerCoaster.contextualStat(
    sortByFilter: SortByFilter,
): String? = "string"
//when (val ride = specs.ride) {
//    is MultiTrackRide -> when (sortByFilter) {
//        DROP -> ride.drop?.maxOfOrNull { it.meters.value.toDisplayFormat() } + " meters"
//        G_FORCE -> ride.gForce?.maxOfOrNull { it.value.toDisplayFormat() } + " G"
//        HEIGHT -> ride.height?.maxOfOrNull { it.meters.value.toDisplayFormat() } + " meters"
//        INVERSIONS -> ride.inversions?.maxOfOrNull { it.value.toString() } + " inversions"
//        LENGTH -> ride.length?.maxOfOrNull { it.meters.value.toDisplayFormat() } + " meters"
//        MAX_VERTICAL -> ride.maxVertical?.maxOfOrNull { it.degrees.value.toString() } + "°"
//        SPEED -> ride.speed?.maxOfOrNull { it.kmh.value.toDisplayFormat() } + " km/h"
//        ALPHABETICAL -> null
//    }
//
//    is SingleTrackRide ->
//        when (sortByFilter) {
//        ALPHABETICAL -> null
//        DROP -> ride.drop?.meters?.value?.toDisplayFormat() + " meters"
//        G_FORCE -> ride.gForce?.value?.toDisplayFormat() + " G"
//        HEIGHT -> ride.height?.meters?.value?.toDisplayFormat() + " meters"
//        INVERSIONS -> ride.inversions?.value.toString() + " inversions"
//        LENGTH -> ride.length?.meters?.value?.toDisplayFormat() + " meters"
//        MAX_VERTICAL -> ride.maxVertical?.degrees?.value.toString() + "°"
//        SPEED -> ride.speed?.kmh?.value?.toDisplayFormat() + " km/h"
//    }
//
//    null -> null
//}
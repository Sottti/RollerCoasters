package com.sottti.roller.coasters.presentation.explore.data

import androidx.paging.PagingData
import androidx.paging.map
import com.sottti.roller.coasters.domain.roller.coasters.model.MultiTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SingleTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.Alphabetical
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.Drop
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.GForce
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.Height
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.Inversions
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.Length
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.MaxVertical
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.Speed
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.utils.format.UnitDisplayFormatter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun Flow<PagingData<RollerCoaster>>.toUiModel(
    appLanguage: AppLanguage,
    sortByFilter: SortByFilter,
    unitDisplayFormatter: UnitDisplayFormatter,
): Flow<PagingData<ExploreRollerCoaster>> =
    map { pagingData ->
        var currentRank = 1
        var previousStat: String? = null
        var itemsWithSameStat = 0

        pagingData.map { rollerCoaster ->
            val currentStat = rollerCoaster.contextualStat(appLanguage, sortByFilter, unitDisplayFormatter)

            when {
                currentStat != null && currentStat == previousStat -> itemsWithSameStat++
                else -> {
                    currentRank += itemsWithSameStat
                    itemsWithSameStat = 1
                }
            }

            previousStat = currentStat

            rollerCoaster.toUiModel(appLanguage, currentRank, sortByFilter, unitDisplayFormatter)
        }
    }

private fun RollerCoaster.toUiModel(
    appLanguage: AppLanguage,
    ranking: Int,
    sortByFilter: SortByFilter,
    unitDisplayFormatter: UnitDisplayFormatter,
) = ExploreRollerCoaster(
    imageUrl = pictures.main?.url,
    parkName = park.name.value,
    rollerCoasterName = name.current.value,
    stat = contextualStat(appLanguage, sortByFilter, unitDisplayFormatter),
    statDetail = "Ranked #$ranking",
)

private fun RollerCoaster.contextualStat(
    appLanguage: AppLanguage,
    sortByFilter: SortByFilter,
    unitDisplayFormatter: UnitDisplayFormatter,
): String? = when (val ride = specs.ride) {
    is MultiTrackRide -> when (sortByFilter) {
        Alphabetical -> null
        Drop -> ride.drop?.maxOfOrNull { unitDisplayFormatter.toDisplayFormat(appLanguage, it) }
        GForce -> ride.gForce?.maxOfOrNull { unitDisplayFormatter.toDisplayFormat(appLanguage, it) }
        Height -> ride.height?.maxOfOrNull { unitDisplayFormatter.toDisplayFormat(appLanguage, it) }
        Inversions -> ride.inversions?.maxOfOrNull { unitDisplayFormatter.toDisplayFormat(it) }
        Length -> ride.length?.maxOfOrNull { unitDisplayFormatter.toDisplayFormat(appLanguage, it) }
        MaxVertical -> ride.maxVertical?.maxOfOrNull { unitDisplayFormatter.toDisplayFormat(it) }
        Speed -> ride.speed?.maxOfOrNull { unitDisplayFormatter.toDisplayFormat(appLanguage, it) }
    }

    is SingleTrackRide ->
        when (sortByFilter) {
            Alphabetical -> null
            Drop -> ride.drop?.let { unitDisplayFormatter.toDisplayFormat(appLanguage, it) }
            GForce -> ride.gForce?.let { unitDisplayFormatter.toDisplayFormat(appLanguage, it) }
            Height -> ride.height?.let { unitDisplayFormatter.toDisplayFormat(appLanguage, it) }
            Inversions -> ride.inversions?.let { unitDisplayFormatter.toDisplayFormat(it) }
            Length -> ride.length?.let { unitDisplayFormatter.toDisplayFormat(appLanguage, it) }
            MaxVertical -> ride.maxVertical?.let { unitDisplayFormatter.toDisplayFormat(it) }
            Speed -> ride.speed?.let { unitDisplayFormatter.toDisplayFormat(appLanguage, it) }
        }

    null -> null
}
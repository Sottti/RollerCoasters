package com.sottti.roller.coasters.presentation.explore.data

import androidx.paging.PagingData
import androidx.paging.map
import com.sottti.roller.coasters.domain.roller.coasters.model.MultiTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SingleTrackRide
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.ALPHABETICAL
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.DROP
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.G_FORCE
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.HEIGHT
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.INVERSIONS
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.LENGTH
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.MAX_VERTICAL
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.SPEED
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.utils.format.UnitFormatter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun Flow<PagingData<RollerCoaster>>.toUiModel(
    appLanguage: AppLanguage,
    sortByFilter: SortByFilter,
    unitFormatter: UnitFormatter,
): Flow<PagingData<ExploreRollerCoaster>> =
    map { pagingData ->
        var currentRank = 1
        var previousStat: String? = null
        var itemsWithSameStat = 0

        pagingData.map { rollerCoaster ->
            val currentStat = rollerCoaster.contextualStat(appLanguage, sortByFilter, unitFormatter)

            when {
                currentStat != null && currentStat == previousStat -> itemsWithSameStat++
                else -> {
                    currentRank += itemsWithSameStat
                    itemsWithSameStat = 1
                }
            }

            previousStat = currentStat

            rollerCoaster.toUiModel(appLanguage, currentRank, sortByFilter, unitFormatter)
        }
    }

private fun RollerCoaster.toUiModel(
    appLanguage: AppLanguage,
    ranking: Int,
    sortByFilter: SortByFilter,
    unitFormatter: UnitFormatter,
) = ExploreRollerCoaster(
    imageUrl = pictures.main?.url,
    parkName = park.name.value,
    rollerCoasterName = name.current.value,
    stat = contextualStat(appLanguage, sortByFilter, unitFormatter),
    statDetail = "Ranked #$ranking",
)

private fun RollerCoaster.contextualStat(
    appLanguage: AppLanguage,
    sortByFilter: SortByFilter,
    unitFormatter: UnitFormatter,
): String? = when (val ride = specs.ride) {
    is MultiTrackRide -> when (sortByFilter) {
        ALPHABETICAL -> null
        DROP -> ride.drop?.maxOfOrNull { unitFormatter.toDisplayFormat(appLanguage, it) }
        G_FORCE -> ride.gForce?.maxOfOrNull { unitFormatter.toDisplayFormat(appLanguage, it) }
        HEIGHT -> ride.height?.maxOfOrNull { unitFormatter.toDisplayFormat(appLanguage, it) }
        INVERSIONS -> ride.inversions?.maxOfOrNull { unitFormatter.toDisplayFormat(it) }
        LENGTH -> ride.length?.maxOfOrNull { unitFormatter.toDisplayFormat(appLanguage, it) }
        MAX_VERTICAL -> ride.maxVertical?.maxOfOrNull { unitFormatter.toDisplayFormat(it) }
        SPEED -> ride.speed?.maxOfOrNull { unitFormatter.toDisplayFormat(appLanguage, it) }
    }

    is SingleTrackRide ->
        when (sortByFilter) {
            ALPHABETICAL -> null
            DROP -> ride.drop?.let { unitFormatter.toDisplayFormat(appLanguage, it) }
            G_FORCE -> ride.gForce?.let { unitFormatter.toDisplayFormat(appLanguage, it) }
            HEIGHT -> ride.height?.let { unitFormatter.toDisplayFormat(appLanguage, it) }
            INVERSIONS -> ride.inversions?.let { unitFormatter.toDisplayFormat(it) }
            LENGTH -> ride.length?.let { unitFormatter.toDisplayFormat(appLanguage, it) }
            MAX_VERTICAL -> ride.maxVertical?.let { unitFormatter.toDisplayFormat(it) }
            SPEED -> ride.speed?.let { unitFormatter.toDisplayFormat(appLanguage, it) }
        }

    null -> null
}
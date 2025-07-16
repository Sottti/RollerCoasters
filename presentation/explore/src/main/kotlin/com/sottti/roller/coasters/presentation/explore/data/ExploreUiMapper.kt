package com.sottti.roller.coasters.presentation.explore.data

import androidx.paging.PagingData
import androidx.paging.map
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop
import com.sottti.roller.coasters.domain.roller.coasters.model.GForce
import com.sottti.roller.coasters.domain.roller.coasters.model.Height
import com.sottti.roller.coasters.domain.roller.coasters.model.Inversions
import com.sottti.roller.coasters.domain.roller.coasters.model.Length
import com.sottti.roller.coasters.domain.roller.coasters.model.MaxVertical
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
import com.sottti.roller.coasters.domain.roller.coasters.model.Speed
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.presentation.explore.R
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.format.DisplayUnitFormatter
import com.sottti.roller.coasters.presentation.string.provider.StringProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Locale

internal fun Flow<PagingData<RollerCoaster>>.toUiModel(
    appLanguage: AppLanguage,
    sortByFilter: SortByFilter,
    stringProvider: StringProvider,
    systemLocale: Locale,
    displayUnitFormatter: DisplayUnitFormatter,
): Flow<PagingData<ExploreRollerCoaster>> = map { pagingData ->
    var currentRank = 1
    var previousStat: String? = null
    var itemsWithSameStat = 0

    pagingData.map { rollerCoaster ->
        val currentStat = rollerCoaster.contextualStat(
            appLanguage = appLanguage,
            systemLocale = systemLocale,
            sortByFilter = sortByFilter,
            displayUnitFormatter = displayUnitFormatter,
        )

        when {
            currentStat != null && currentStat == previousStat -> itemsWithSameStat++
            else -> {
                currentRank += itemsWithSameStat
                itemsWithSameStat = 1
            }
        }

        previousStat = currentStat

        rollerCoaster.toUiModel(
            appLanguage = appLanguage,
            systemLocale = systemLocale,
            ranking = currentRank,
            sortByFilter = sortByFilter,
            stringProvider = stringProvider,
            displayUnitFormatter = displayUnitFormatter,
        )
    }
}

private fun RollerCoaster.toUiModel(
    appLanguage: AppLanguage,
    systemLocale: Locale,
    ranking: Int,
    sortByFilter: SortByFilter,
    stringProvider: StringProvider,
    displayUnitFormatter: DisplayUnitFormatter,
) = ExploreRollerCoaster(
    id = id.value,
    imageUrl = pictures.main?.url,
    parkName = park.name.value,
    rollerCoasterName = name.current.value,
    stat = contextualStat(appLanguage, systemLocale, sortByFilter, displayUnitFormatter),
    statDetail = stringProvider.getString(R.string.roller_coaster_ranking, ranking),
)

private fun RollerCoaster.contextualStat(
    appLanguage: AppLanguage,
    systemLocale: Locale,
    sortByFilter: SortByFilter,
    displayUnitFormatter: DisplayUnitFormatter,
): String? {
    val formatContext = FormatContext(
        appLanguage = appLanguage,
        systemLocale = systemLocale,
        displayUnitFormatter = displayUnitFormatter,
    )
    return when (val ride = specs.ride) {
        is MultiTrackRide -> when (sortByFilter) {
            Alphabetical -> null
            Drop -> ride.drop?.maxOfOrNull { formatContext.formatDrop(it) }
            GForce -> ride.gForce?.maxOfOrNull { formatContext.formatGForce(it) }
            Height -> ride.height?.maxOfOrNull { formatContext.formatHeight(it) }
            Inversions -> ride.inversions?.maxOfOrNull { formatContext.formatInversions(it) }
            Length -> ride.length?.maxOfOrNull { formatContext.formatLength(it) }
            MaxVertical -> ride.maxVertical?.maxOfOrNull { formatContext.formatMaxVertical(it) }
            Speed -> ride.speed?.maxOfOrNull { formatContext.formatSpeed(it) }
        }

        is SingleTrackRide -> when (sortByFilter) {
            Alphabetical -> null
            Drop -> ride.drop?.let { formatContext.formatDrop(it) }
            GForce -> ride.gForce?.let { formatContext.formatGForce(it) }
            Height -> ride.height?.let { formatContext.formatHeight(it) }
            Inversions -> ride.inversions?.let { formatContext.formatInversions(it) }
            Length -> ride.length?.let { formatContext.formatLength(it) }
            MaxVertical -> ride.maxVertical?.let { formatContext.formatMaxVertical(it) }
            Speed -> ride.speed?.let { formatContext.formatSpeed(it) }
        }

        null -> null
    }
}

private data class FormatContext(
    val appLanguage: AppLanguage,
    val systemLocale: Locale,
    val displayUnitFormatter: DisplayUnitFormatter,
)

private fun FormatContext.formatDrop(drop: Drop) =
    displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, drop)

private fun FormatContext.formatGForce(gForce: GForce) =
    displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, gForce)

private fun FormatContext.formatHeight(height: Height) =
    displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, height)

private fun FormatContext.formatLength(length: Length) =
    displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, length)

private fun FormatContext.formatSpeed(speed: Speed) =
    displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, speed)

private fun FormatContext.formatInversions(inversions: Inversions) =
    displayUnitFormatter.toDisplayFormat(inversions)

private fun FormatContext.formatMaxVertical(maxVertical: MaxVertical) =
    displayUnitFormatter.toDisplayFormat(maxVertical)
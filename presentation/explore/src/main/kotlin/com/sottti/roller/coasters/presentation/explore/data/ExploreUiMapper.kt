package com.sottti.roller.coasters.presentation.explore.data

import androidx.paging.PagingData
import androidx.paging.map
import com.sottti.roller.coasters.domain.roller.coasters.model.Drop
import com.sottti.roller.coasters.domain.roller.coasters.model.GForce
import com.sottti.roller.coasters.domain.roller.coasters.model.Height
import com.sottti.roller.coasters.domain.roller.coasters.model.Inversions
import com.sottti.roller.coasters.domain.roller.coasters.model.Length
import com.sottti.roller.coasters.domain.roller.coasters.model.MaxVertical
import com.sottti.roller.coasters.domain.roller.coasters.model.Ride
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
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
import com.sottti.roller.coasters.domain.roller.coasters.model.helpers.maxDrop
import com.sottti.roller.coasters.domain.roller.coasters.model.helpers.maxGForce
import com.sottti.roller.coasters.domain.roller.coasters.model.helpers.maxHeight
import com.sottti.roller.coasters.domain.roller.coasters.model.helpers.maxInversions
import com.sottti.roller.coasters.domain.roller.coasters.model.helpers.maxLength
import com.sottti.roller.coasters.domain.roller.coasters.model.helpers.maxMaxVertical
import com.sottti.roller.coasters.domain.roller.coasters.model.helpers.maxSpeed
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
        val currentStat = rollerCoaster.specs.ride?.contextualStat(
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

        ExploreRollerCoaster(
            id = rollerCoaster.id.value,
            imageUrl = rollerCoaster.pictures.main?.url,
            parkName = rollerCoaster.park.name.value,
            rollerCoasterName = rollerCoaster.name.current.value,
            stat = currentStat,
            statDetail = stringProvider.getString(R.string.roller_coaster_ranking, currentRank),
        )

    }
}

private fun Ride.contextualStat(
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
    return when (sortByFilter) {
        Alphabetical -> null
        Drop -> maxDrop()?.let { formatContext.formatDrop(it) }
        GForce -> maxGForce()?.let { formatContext.formatGForce(it) }
        Height -> maxHeight()?.let { formatContext.formatHeight(it) }
        Inversions -> maxInversions()?.let { formatContext.formatInversions(it) }
        Length -> maxLength()?.let { formatContext.formatLength(it) }
        MaxVertical -> maxMaxVertical()?.let { formatContext.formatMaxVertical(it) }
        Speed -> maxSpeed()?.let { formatContext.formatSpeed(it) }
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
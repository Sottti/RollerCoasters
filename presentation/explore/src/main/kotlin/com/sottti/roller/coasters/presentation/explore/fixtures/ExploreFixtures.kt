package com.sottti.roller.coasters.presentation.explore.fixtures

import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoasterId
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.fixtures.anotherStat
import com.sottti.roller.coasters.presentation.fixtures.anotherStatDetail
import com.sottti.roller.coasters.presentation.fixtures.stat
import com.sottti.roller.coasters.presentation.fixtures.statDetail

internal fun exploreRollerCoasters() =
    listOf(
        exploreRollerCoaster(),
        anotherExploreRollerCoaster(),
    )

private fun exploreRollerCoaster(): ExploreRollerCoaster =
    ExploreRollerCoaster(
        id = rollerCoaster.id.value,
        imageUrl = rollerCoaster.pictures.main?.url,
        parkName = rollerCoaster.park.name.value,
        rollerCoasterName = rollerCoaster.name.current.value,
        stat = stat,
        statDetail = statDetail,
    )

private fun anotherExploreRollerCoaster(): ExploreRollerCoaster =
    ExploreRollerCoaster(
        id = rollerCoaster.id.value,
        imageUrl = anotherRollerCoaster.pictures.main?.url,
        parkName = anotherRollerCoaster.park.name.value,
        rollerCoasterName = anotherRollerCoaster.name.current.value,
        stat = anotherStat,
        statDetail = anotherStatDetail,
    )
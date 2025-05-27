package com.sottti.roller.coasters.presentation.explore.fixtures

import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.fixtures.fixtureAnotherStat
import com.sottti.roller.coasters.presentation.fixtures.fixtureAnotherStatDetail
import com.sottti.roller.coasters.presentation.fixtures.fixtureStatDetail
import com.sottti.roller.coasters.presentation.fixtures.fixtureState

internal fun exploreRollerCoasters() =
    listOf(
        exploreRollerCoaster(),
        anotherExploreRollerCoaster(),
    )

private fun exploreRollerCoaster(): ExploreRollerCoaster =
    ExploreRollerCoaster(
        id = rollerCoaster().id.value,
        imageUrl = rollerCoaster().pictures.main?.url,
        parkName = rollerCoaster().park.name.value,
        rollerCoasterName = rollerCoaster().name.current.value,
        stat = fixtureState,
        statDetail = fixtureStatDetail,
    )

private fun anotherExploreRollerCoaster(): ExploreRollerCoaster =
    ExploreRollerCoaster(
        id = rollerCoaster().id.value,
        imageUrl = anotherRollerCoaster().pictures.main?.url,
        parkName = anotherRollerCoaster().park.name.value,
        rollerCoasterName = anotherRollerCoaster().name.current.value,
        stat = fixtureAnotherStat,
        statDetail = fixtureAnotherStatDetail,
    )
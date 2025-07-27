package com.sottti.roller.coasters.presentation.favourites.data

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster
import org.junit.Test

internal class FavouritesMapperTest {

    @Test
    fun `maps domain roller coaster to UI model correctly`() {
        val domain = rollerCoaster()
        val result = domain.toUiModel()
        assertThat(result).isEqualTo(
            FavouritesRollerCoaster(
                id = domain.id.value,
                imageUrl = domain.pictures.main?.url,
                name = domain.name.current.value,
                parkName = domain.park.name.value,
            ),
        )
    }

    @Test
    fun `maps domain model with null main picture to null imageUrl`() {
        val domain = rollerCoaster()
        val domainWithoutPictures = domain.copy(pictures = domain.pictures.copy(main = null))
        val result = domainWithoutPictures.toUiModel()
        assertThat(result.imageUrl).isNull()
    }
}

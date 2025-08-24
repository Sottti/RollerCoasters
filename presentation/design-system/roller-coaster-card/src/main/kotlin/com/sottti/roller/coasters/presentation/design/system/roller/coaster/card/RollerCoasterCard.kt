package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.large.LargeImpl
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.small.SmallImpl

public object RollerCoasterCard {

    @Composable
    public fun Small(
        imageUrl: ImageUrl?,
        parkName: String,
        rollerCoasterName: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        foreverLoading: Boolean = false,
    ) {
        SmallImpl(
            modifier = modifier,
            onClick = onClick,
            imageUrl = imageUrl,
            foreverLoading = foreverLoading,
            parkName = parkName,
            rollerCoasterName = rollerCoasterName,
        )
    }

    @Composable
    public fun Large(
        imageUrl: ImageUrl?,
        parkName: String,
        rollerCoasterName: String,
        stat: RollerCoasterCardStat?,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        foreverLoading: Boolean = false,
    ) {
        LargeImpl(
            modifier = modifier,
            onClick = onClick,
            imageUrl = imageUrl,
            foreverLoading = foreverLoading,
            parkName = parkName,
            rollerCoasterName = rollerCoasterName,
            stat = stat,
        )
    }
}
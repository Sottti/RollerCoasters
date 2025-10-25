package com.sotti.roller.coasters.presentation.design.system.map

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sotti.roller.coasters.domain.fixtures.CITY
import com.sotti.roller.coasters.domain.fixtures.LATITUDE
import com.sotti.roller.coasters.domain.fixtures.LONGITUDE

internal class MapStateProvider : PreviewParameterProvider<MapState> {
    override val values = sequence {
        yield(
            MapState(
                latitude = LATITUDE,
                longitude = LONGITUDE,
                markerTitle = CITY,
                modifier = Modifier,
            )
        )
    }
}

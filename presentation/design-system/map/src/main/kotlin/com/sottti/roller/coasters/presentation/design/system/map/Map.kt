package com.sottti.roller.coasters.presentation.design.system.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState
import com.sottti.roller.coasters.presentation.design.system.themes.getMapStyle

@Composable
public fun Map(
    latitude: Double,
    longitude: Double,
    markerTitle: String,
    modifier: Modifier,
) {
    val latLng = LatLng(latitude, longitude)
    val context = LocalContext.current
    val markerState = rememberUpdatedMarkerState(position = latLng)
    val mapStyleOptions = remember { MapStyleOptions.loadRawResourceStyle(context, getMapStyle()) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 17f)
    }
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            isBuildingEnabled = true,
            isIndoorEnabled = true,
            mapStyleOptions = mapStyleOptions,
            mapType = MapType.NORMAL,
        ),
        uiSettings = MapUiSettings(scrollGesturesEnabled = false)
    ) {
        Marker(state = markerState, title = markerTitle)
    }
}
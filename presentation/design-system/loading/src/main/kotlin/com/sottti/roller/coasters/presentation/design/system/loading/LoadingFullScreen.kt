package com.sottti.roller.coasters.presentation.design.system.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme

@Composable
public fun LoadingFullScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        LoadingIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
@PreviewLightDark
private fun LoadingFullScreenPreview() {
    RollerCoastersPreviewTheme {
        LoadingFullScreen()
    }
}
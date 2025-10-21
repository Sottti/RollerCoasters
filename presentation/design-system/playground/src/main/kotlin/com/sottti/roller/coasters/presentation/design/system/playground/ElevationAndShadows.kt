package com.sottti.roller.coasters.presentation.design.system.playground

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.shapes.shapes
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import kotlin.math.roundToInt

@Composable
@Preview(
    widthDp = 1200,
    heightDp = 600,
)
private fun cardElevationAnimation() {
    Row {
        LightTheme { ElevatingCard() }
        DarkTheme { ElevatingCard() }
    }
}

@Composable
private fun RowScope.ElevatingCard() {
    Box(
        modifier = Modifier
            .background(color = colors.background)
            .padding(vertical = 180.dp, horizontal = 120.dp)
            .fillMaxHeight()
            .weight(1.0f),
        contentAlignment = Alignment.Center,
    ) {
        val elevation by infiniteElevationAnimation(
            start = 0.dp,
            end = 24.dp,
        )

        Surface(
            color = colors.surface,
            shadowElevation = elevation,
            tonalElevation = elevation,
            shape = shapes.roundedCorner.large,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                val elevationInDps =
                    with(LocalDensity.current) { elevation.value.roundToInt() }
                Text(
                    text = "Elevation is ${elevationInDps}dp",
                    modifier = Modifier,
                )
            }
        }
    }
}

@Composable
private fun infiniteElevationAnimation(
    start: Dp,
    end: Dp,
): State<Dp> {
    val infiniteTransition = rememberInfiniteTransition(label = "elevation")
    val animationDuration = 4000
    val delay = 500
    return infiniteTransition.animateValue(
        initialValue = start,
        targetValue = end,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationDuration + (delay * 2)
                start at delay using FastOutSlowInEasing
                end at delay + animationDuration
            },
            repeatMode = RepeatMode.Reverse,
        ),
        label = "elevation"
    )
}

@Composable
private fun LightTheme(
    content: @Composable () -> Unit,
) {
    LightDarkTheme(
        darkTheme = false,
        content = content
    )
}

@Composable
private fun DarkTheme(
    content: @Composable () -> Unit,
) {
    LightDarkTheme(
        darkTheme = true,
        content = content
    )
}


@Composable
private fun LightDarkTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    val configuration = LocalConfiguration.current

    val newConfiguration = Configuration(configuration).apply {
        uiMode = when {
            darkTheme -> Configuration.UI_MODE_NIGHT_YES
            else -> Configuration.UI_MODE_NIGHT_NO
        }
        uiMode = uiMode or Configuration.UI_MODE_TYPE_NORMAL
    }

    CompositionLocalProvider(
        LocalConfiguration provides newConfiguration
    ) {

        RollerCoastersTheme(content = content, dynamicColor = ResolvedDynamicColor(false))
    }
}

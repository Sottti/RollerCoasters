package com.sotti.roller.coasters.presentation.design.system.playground

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sotti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
private fun AnimatedCard() {
    var expanded by remember { mutableStateOf(false) }
    val height by animateDpAsState(
        targetValue = if (expanded) 120.dp else 60.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        )
    )
    val width by animateDpAsState(
        targetValue = if (expanded) 180.dp else 90.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        )

    )
    OutlinedCard(
        modifier = Modifier.wrapContentSize(),
        onClick = { expanded = !expanded },
    ) {
        Box(
            modifier = Modifier
                .height(height)
                .width(width)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Colours", color = Color.Black,
            )
        }
    }
}

@Composable
@Preview(widthDp = 200, heightDp = 200)
private fun AnimatedCardTransition() {
    val springSpec = spring<Dp>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMedium
    )

    var isExpanded by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isExpanded, label = "Card Transition")
    val containerColor by transition.animateColor(label = "Container Color") { expanded ->
        if (expanded) Color.Yellow else Color.White
    }
    val textColor by transition.animateColor(label = "Text Color") { expanded ->
        if (expanded) Color.Black else Color.DarkGray
    }
    val borderColor by transition.animateColor(label = "Border Color") { expanded ->
        if (expanded) Color.Red else Color.DarkGray
    }
    val height by transition.animateDp({ springSpec }, label = "Height") { expanded ->
        if (expanded) 90.dp else 60.dp
    }
    val width by transition.animateDp(
        transitionSpec = { springSpec },
        label = "Width"
    ) { expanded ->
        if (expanded) 180.dp else 120.dp
    }

    OutlinedCard(
        onClick = { isExpanded = !isExpanded },
        modifier = Modifier.wrapContentSize(),
        colors = CardDefaults.outlinedCardColors(containerColor = containerColor),
        border = BorderStroke(1.dp, borderColor),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(width = width, height = height)
        ) {
            val text = if (isExpanded) "BOOOM! 💥" else "Click me"
            Text(text = text, color = textColor)
        }
    }
}

@Composable
private fun BackgroundArg() {
    val backgroundColor by animateColorBetween(Color.Magenta, Color.Green)
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .clip(RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {
        Text(text = "Colours", color = Color.Black)
    }
}

@Composable
internal fun animateColorBetween(start: Color, end: Color): State<Color> {
    val infiniteTransition = rememberInfiniteTransition()
    return infiniteTransition.animateColor(
        initialValue = start,
        targetValue = end,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2.seconds.inWholeMilliseconds.toInt()),
            repeatMode = RepeatMode.Reverse,
        )
    )
}

@Composable
private fun Alpha1() {
    val alpha = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        alpha.animateTo(targetValue = 1f, tween(5000))
    }
    Box(Modifier.alpha(alpha = alpha.value)) {
        Text(text = "Colours", color = Color.Black)
    }
}

@Composable
private fun PulsatingAlpha() {
    val pulseRateMs by remember { mutableLongStateOf(2000) }
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        while (isActive) {
            alpha.animateTo(0f, animationSpec = tween(durationMillis = 300))
            delay(pulseRateMs)
            alpha.animateTo(1f, animationSpec = tween(durationMillis = 300))
        }
    }

    Box(Modifier.alpha(alpha = alpha.value)) {
        Text(text = "Colours", color = Color.Black)
    }
}

@Composable
private fun PulsatingAlphaOnClick() {
    val pulseRateMs by remember { mutableLongStateOf(2000) }
    val alpha = remember { Animatable(1f) }
    val coroutine = rememberCoroutineScope()
    val onClick: () -> Unit = {
        coroutine.launch {
            while (isActive) {
                alpha.animateTo(0f, animationSpec = tween(durationMillis = 300))
                alpha.animateTo(1f, animationSpec = tween(durationMillis = 300))
                delay(pulseRateMs)
            }
        }
    }
    Box(
        Modifier
            .alpha(alpha = alpha.value)
            .clickable { onClick() }) {
        Text(text = "Colours", color = Color.Black)
    }
}

@Composable
private fun animateAlphaBetween(start: Float, end: Float): State<Float> {
    val infiniteTransition = rememberInfiniteTransition()
    return infiniteTransition.animateFloat(
        initialValue = start,
        targetValue = end,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2.seconds.inWholeMilliseconds.toInt()),
            repeatMode = RepeatMode.Reverse,
        )
    )
}

@Composable
private fun MyPreview() {
    RollerCoastersTheme {
    }
}

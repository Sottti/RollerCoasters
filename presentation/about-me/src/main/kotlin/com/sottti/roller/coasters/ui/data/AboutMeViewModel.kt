package com.sottti.roller.coasters.ui.data

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AboutMeViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(generateRandomColor())
    val state: StateFlow<Color> = _state.asStateFlow()
}

private fun generateRandomColor(): Color {
    val red = Random.nextInt(from = 0, until = 256)
    val green = Random.nextInt(from = 0, until = 256)
    val blue = Random.nextInt(from = 0, until = 256)

    return Color(red, green, blue)
}
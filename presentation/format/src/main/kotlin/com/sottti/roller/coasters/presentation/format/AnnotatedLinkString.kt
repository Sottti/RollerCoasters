package com.sottti.roller.coasters.presentation.format

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

@Composable
public fun annotatedLinkString(
    color: Color,
    text: String,
    url: String,
): AnnotatedString = buildAnnotatedString {
    withStyle(
        style = SpanStyle(
            color = color,
            textDecoration = TextDecoration.Underline,
        )
    ) {
        append(text)
    }
    addStringAnnotation(
        tag = "URL",
        annotation = url,
        start = 0,
        end = text.length,
    )
}

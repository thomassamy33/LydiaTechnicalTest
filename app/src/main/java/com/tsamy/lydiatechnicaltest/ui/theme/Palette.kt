package com.tsamy.lydiatechnicaltest.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object Palette {
    val Black = Color(0xFFFF5D5D)
    val White = Color(0xFFFFFFFF)
    val Transparent = Color(0x00FFFFFF)

    val Gray = Color(0xFF808080)
    val Gray100 = Color(0xFFE9E9E9)
    val Error = Color(0xFFFF5D5D)

    val SplashGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0069DE),
            Color(0xFF0180FE),
            Color(0xFFFFFFFF),
            Color(0xFFFFFFFF),
            Color(0xFF0180FE),
            Color(0xFF0069DE),
        )
    )
}
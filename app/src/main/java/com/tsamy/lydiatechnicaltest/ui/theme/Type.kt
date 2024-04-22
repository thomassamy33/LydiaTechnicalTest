package com.tsamy.lydiatechnicaltest.ui.theme

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Typography {

    val TitleM: TextStyle
        get() = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        )

    val TitleMMedium: TextStyle
        get() = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        )

    val SubtitleL: TextStyle
        get() = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        )
    val ParagraphLBold: TextStyle
        get() = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 21.6.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        )

    val ParagraphLLight: TextStyle
        get() = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 21.6.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        )

    val ParagraphLRegular: TextStyle
        get() = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 21.6.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        )

    val ParagraphM: TextStyle
        get() = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        )

    val ParagraphMMedium: TextStyle
        get() = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        )

    val ParagraphXS: TextStyle
        get() = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 18.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        )
}
package com.example.gd.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.LightGray

private val DarkColorPalette = darkColors(
    onPrimary = White, // 글자색
    primary = Black, // 배경색
    primaryVariant = Point, // 포인트색
    secondary = Gray, // 선택안된 BotomScreen icon & 검색창 돋보기와 X 아이콘
    onSecondary = SearchBarBG, // // 검색창 색
    secondaryVariant = SearchBarTT // 검색창 글씨
// black 테마는 수정 필요
)

private val LightColorPalette = lightColors(
    onPrimary = Black, // 글자색
    primary = White, // 배경색
    primaryVariant = Point, // 포인트색
    secondary = Gray, // 선택안된 BotomScreen icon & 검색창 돋보기와 X 아이콘
    onSecondary = CustomGray, // // 검색창 색
    secondaryVariant = LightGray // 검색창 글씨
)

/* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */

@Composable
fun GdTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = LightColorPalette
        /*
        if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

         */

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
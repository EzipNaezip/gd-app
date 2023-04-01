package com.example.gd.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gd.R


val suite = FontFamily(
    Font(R.font.suite_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.suite_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.suite_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.suite_normal, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.suite_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.suite_light, FontWeight.Light, FontStyle.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = suite,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    )


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
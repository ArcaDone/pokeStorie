package com.arcadone.pokestorie.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arcadone.pokestorie.R

val robotoFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium)
)

@Immutable
data class DesignTypography(
    val label: TextStyle,
    val heading: TextStyle,
    val headingLarge: TextStyle,
)

val defaultDesignTypography = DesignTypography(
    label = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    heading = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    headingLarge = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    )
)

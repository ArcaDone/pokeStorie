package com.arcadone.pokestorie.ui.theme

import android.graphics.Shader
import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.ShaderBrush

val largeRadialGradient = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }
}

@Immutable
data class DesignColorPalette(
    val dsColorWater: Color,
    val dsColorSecondary: Color,
    val dsColorTertiary: Color,
    val dsColorOrange: Color,
    val dsColorBlack: Color,
    val dsColorWhite: Color,
    val lightBlue: Color,
    val dsColorMustard: Color,
    val dsColorDarkBlue: Color,
    val dsColorGrey01: Color,
    val dsColorGrey02: Color,
    val dsColorGrey03: Color,
    val dsColorGrey04: Color,
    val dsColorGrey05: Color,
    val dsColorGrey06: Color,
    val dsColorGrey07: Color,
    val dsColorLightSea: Color,
    val dsColorLightMauve: Color,
)

@Immutable
data class DesignColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val orange: Color,
    val black: Color,
    val white: Color,
    val lightBlue: Color,
    val mustard: Color,
    val darkBlue: Color,
    val grey01: Color,
    val grey02: Color,
    val grey03: Color,
    val grey04: Color,
    val grey05: Color,
    val grey06: Color,
    val grey07: Color,
    val lightSea: Color,
    val lightMauve: Color,
)

fun designTheme(palette: DesignColorPalette) = DesignColors(
    primary = palette.dsColorWater,
    secondary = palette.dsColorSecondary,
    tertiary = palette.dsColorTertiary,
    orange = palette.dsColorOrange,
    black = palette.dsColorBlack,
    white = palette.dsColorWhite,
    lightBlue = palette.dsColorWhite,
    mustard = palette.dsColorMustard,
    darkBlue = palette.dsColorDarkBlue,
    grey01 = palette.dsColorGrey01,
    grey02 = palette.dsColorGrey02,
    grey03 = palette.dsColorGrey03,
    grey04 = palette.dsColorGrey04,
    grey05 = palette.dsColorGrey05,
    grey06 = palette.dsColorGrey06,
    grey07 = palette.dsColorGrey07,
    lightSea = palette.dsColorLightSea,
    lightMauve = palette.dsColorLightMauve,
)

fun darkDesignTheme(palette: DesignColorPalette) = DesignColors(
    primary = palette.dsColorWater,
    secondary = palette.dsColorSecondary,
    tertiary = palette.dsColorTertiary,
    orange = palette.dsColorOrange,
    black = palette.dsColorBlack,
    white = palette.dsColorWhite,
    lightBlue = palette.dsColorWhite,
    mustard = palette.dsColorMustard,
    darkBlue = palette.dsColorDarkBlue,
    grey01 = palette.dsColorGrey01,
    grey02 = palette.dsColorGrey02,
    grey03 = palette.dsColorGrey03,
    grey04 = palette.dsColorGrey04,
    grey05 = palette.dsColorGrey05,
    grey06 = palette.dsColorGrey06,
    grey07 = palette.dsColorGrey07,
    lightSea = palette.dsColorLightSea,
    lightMauve = palette.dsColorLightMauve,
)

val defaultAppPalette = DesignColorPalette(
    dsColorWater = Color(0xFF00a2ab),
    dsColorSecondary = Color(0xFF1C1C1E),
    dsColorTertiary = Color(0xFF58585B),
    dsColorOrange = Color(0xFFeb5c0a),
    dsColorBlack = Color(0xFF000000),
    dsColorWhite = Color(0xFFFFFFFF),
    lightBlue = Color(0xFF2be4dc),
    dsColorMustard = Color(0xFFFFC200),
    dsColorDarkBlue = Color(0xFF243484),
    dsColorGrey01 = Color(0xFF252527),
    dsColorGrey02 = Color(0xFF58585B),
    dsColorGrey03 = Color(0xFF7D7D82),
    dsColorGrey04 = Color(0xFFCBCBCD),
    dsColorGrey05 = Color(0xFFD2D2D2),
    dsColorGrey06 = Color(0xFFE5E5E6),
    dsColorGrey07 = Color(0xFFF2F2F2),
    dsColorLightSea = Color(0xFFF1F8F8),
    dsColorLightMauve = Color(0xFFF4F3F7),
)

package com.arcadone.pokestorie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PokeStorieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorBuilder: ColorsBuilder = ColorsBuilderImpl(defaultAppPalette),
    typography: DesignTypography? = null,
    spacing: DesignSpacing? = null,
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()
    val localTypography = typography ?: defaultDesignTypography
    val localSpacing = spacing ?: defaultSpacing
    SideEffect {
        systemUiController.setNavigationBarColor(
            color = colorBuilder.build(darkTheme).primary
        )
        systemUiController.setStatusBarColor(
            color = colorBuilder.build(darkTheme).white
        )
    }
    CompositionLocalProvider(
        LocalDesignColors provides colorBuilder.build(darkTheme),
        LocalDesignTypography provides localTypography,
        LocalDesignSpacing provides localSpacing
    ) {
        MaterialTheme(
            content = content,
            shapes = Shapes,
            colorScheme = MaterialTheme.colorScheme.copy(primary = ThemeDS.colors.white)
        )
    }
}

val LocalDesignColors = staticCompositionLocalOf {
    DesignColors(
        white = Color.Unspecified,
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        tertiary = Color.Unspecified,
        grey01 = Color.Unspecified,
        grey02 = Color.Unspecified,
        orange = Color.Unspecified,
        grey03 = Color.Unspecified,
        black = Color.Unspecified,
        grey04 = Color.Unspecified,
        darkBlue = Color.Unspecified,
        mustard = Color.Unspecified,
        grey05 = Color.Unspecified,
        grey07 = Color.Unspecified,
        grey06 = Color.Unspecified,
        lightBlue = Color.Unspecified,
        lightSea = Color.Unspecified,
        lightMauve = Color.Unspecified,
    )
}

val LocalDesignTypography = staticCompositionLocalOf {
    DesignTypography(
        label = TextStyle.Default,
        heading = TextStyle.Default,
        headingLarge = TextStyle.Default,
    )
}

val LocalDesignSpacing = staticCompositionLocalOf {
    DesignSpacing(
        space2 = Dp.Unspecified,
        space8 = Dp.Unspecified,
        space10 = Dp.Unspecified,
        space12 = Dp.Unspecified,
        space16 = Dp.Unspecified,
        space24 = Dp.Unspecified,
    )
}

object ThemeDS {
    val colors: DesignColors
        @Composable
        get() = LocalDesignColors.current
    val typography: DesignTypography
        @Composable
        get() = LocalDesignTypography.current
    val spacing: DesignSpacing
        @Composable
        get() = LocalDesignSpacing.current
}
package com.arcadone.pokestorie.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class DesignSpacing(
    val space2: Dp,
    val space8: Dp,
    val space10: Dp,
    val space12: Dp,
    val space16: Dp,
    val space24: Dp,
)

val defaultSpacing = DesignSpacing(
    space2 = 2.dp,
    space8 = 8.dp,
    space10 = 10.dp,
    space12 = 12.dp,
    space16 = 16.dp,
    space24 = 24.dp,
)
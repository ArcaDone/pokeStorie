package com.arcadone.pokestorie.ui.theme

class ColorsBuilderImpl(
    private val palette: DesignColorPalette
) : ColorsBuilder {
    override fun build(isDark: Boolean) = if (isDark)
        darkDesignTheme(palette)
    else
        designTheme(palette)
}
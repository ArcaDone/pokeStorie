package com.arcadone.pokestorie.ui.theme

interface ColorsBuilder {
    fun build(
        isDark: Boolean
    ): DesignColors
}
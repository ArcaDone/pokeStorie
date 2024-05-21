package com.arcadone.pokestorie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arcadone.pokestorie.R
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.ThemeDS

@Composable
fun OverlayLoader() {
    Box(
        Modifier
            .clickable { }
            .fillMaxSize()
            .background(ThemeDS.colors.grey06.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        ImageGIF(gifRes = R.drawable.pokeball_loader)
    }
}


@Preview
@Composable
private fun Preview() {
    PokeStorieAppTheme {
        OverlayLoader()
    }
}
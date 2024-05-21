package com.arcadone.pokestorie.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcadone.pokestorie.R
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.ThemeDS

@Composable
fun SquaredLoaderBox(@DrawableRes gifRes: Int = R.drawable.pokeball_loader) {
    Box(
        Modifier
            .height(100.dp)
            .padding(8.dp)
    ) {
        ImageGIF(gifRes = gifRes, backgroundColor = ThemeDS.colors.grey01)
    }
}


@Preview(device = "id:pixel_2")
@Composable
private fun PokemonLoaderPreview() {
    PokeStorieAppTheme {
        SquaredLoaderBox()
    }
}
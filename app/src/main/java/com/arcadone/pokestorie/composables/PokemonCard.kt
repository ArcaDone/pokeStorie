package com.arcadone.pokestorie.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.arcadone.pokestorie.R
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.ThemeDS

@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    imageSVG: String? = null,
    pokemonName: String,
    placeHolder: Int? = null
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .then(modifier)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.card_pokemon),
            contentDescription = "Card",
            modifier = Modifier
                .fillMaxSize()
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            PText(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = pokemonName,
                color = ThemeDS.colors.orange,
                style = ThemeDS.typography.headingLarge
            )
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(placeHolder ?: imageSVG)
                    .decoderFactory(SvgDecoder.Factory())
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(16.dp),
                filterQuality = FilterQuality.Low,
                contentScale = ContentScale.Fit,
                contentDescription = null
            )
        }

    }
}

@Preview(device = "id:pixel_2")
@Composable
private fun SimplePreview() {
    PokeStorieAppTheme {
        PokemonCard(
            imageSVG = "https://picsum.photos/id/${(10)}/1200/1300",
            pokemonName = "Bulbasaur"
        )
    }
}
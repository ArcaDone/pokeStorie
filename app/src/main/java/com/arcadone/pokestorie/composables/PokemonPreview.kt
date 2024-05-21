package com.arcadone.pokestorie.composables

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme

@Composable
fun PokemonPreview(
    modifier: Modifier = Modifier,
    imageSVG: String?,
) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageSVG)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(true)
            .build(),
        modifier = Modifier
            .then(modifier)
            .aspectRatio(1f)
            .padding(2.dp),
        filterQuality = FilterQuality.Low,
        contentScale = ContentScale.Fit,
        contentDescription = null
    )
}


@Preview(device = "id:pixel_2")
@Composable
private fun PokemonPreviewP() {
    PokeStorieAppTheme {
        PokemonPreview(
            imageSVG = "https://picsum.photos/id/${(10)}/1200/1300"
        )
    }
}
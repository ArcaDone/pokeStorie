package com.arcadone.pokestorie.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.arcadone.pokestorie.R
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.ThemeDS

@Composable
fun ImageGIF(
    modifier: Modifier = Modifier,
    @DrawableRes gifRes: Int = R.drawable.loader,
    backgroundColor: Color = Color.Transparent
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(GifDecoder.Factory())
        }
        .build()
    Image(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .aspectRatio(1f)
            .background(backgroundColor),
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = gifRes).apply(block = {
                    size(Size.ORIGINAL)
                })
                .build(), imageLoader = imageLoader
        ),
        contentDescription = null,
    )
}


@Preview(device = "id:pixel_2")
@Composable
private fun ImageGIFPreview() {
    PokeStorieAppTheme {
        ImageGIF(backgroundColor = ThemeDS.colors.mustard,gifRes = R.drawable.pokeball_loader)
    }
}
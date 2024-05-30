package com.arcadone.pokestorie.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.ThemeDS

@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    imageSVG: String? = null,
    pokemonName: String,
    pokemonDesc: String,
    pokemonTypes: List<String> = emptyList(),
    placeHolder: Int? = null
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(placeHolder ?: imageSVG)
                        .decoderFactory(SvgDecoder.Factory())
                        .crossfade(true)
                        .build(),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(64.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp)),
                    filterQuality = FilterQuality.Medium,
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    PText(
                        text = pokemonName.capitalize(Locale.current),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = ThemeDS.colors.black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    if (pokemonTypes.isNotEmpty()) {

                        Row(modifier = Modifier) {
                            pokemonTypes.forEach { type ->
                                TypeChip(type = type)
                            }
                        }

                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    PText(
                        modifier = Modifier.padding(end = 8.dp),
                        text = pokemonDesc.replace("\n", ""),
                        fontSize = 12.sp,
                        color = Color.Gray,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        style = ThemeDS.typography.label,
                        textAlign = TextAlign.Justify
                    )
                }
            }

        }
    }
}

@Preview(device = "id:pixel_2")
@Composable
private fun SimplePreview() {
    PokeStorieAppTheme {
        PokemonCard(
            imageSVG = "https://picsum.photos/id/${(10)}/1200/1300",
            pokemonName = "Bulbasaur",
            pokemonDesc = "It uses the nutrients that are packed on its back in order to grow.",
            pokemonTypes = listOf("Grass", "Poison")
        )
    }
}
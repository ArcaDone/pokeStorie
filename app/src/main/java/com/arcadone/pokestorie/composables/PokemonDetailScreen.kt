package com.arcadone.pokestorie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.arcadone.pokestorie.domain.model.PokemonInfo
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.Shapes
import com.arcadone.pokestorie.ui.theme.ThemeDS
import com.arcadone.pokestorie.ui.theme.largeRadialGradient

@Composable
fun PokemonDetailScreen(pokemonInfo: PokemonInfo) {

    LazyColumn(
        modifier = Modifier
            .background(largeRadialGradient)
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemonInfo.imageSVG)
                    .decoderFactory(SvgDecoder.Factory())
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                filterQuality = FilterQuality.Low,
                contentScale = ContentScale.Fit,
                contentDescription = null
            )
        }

        item {
            PText(
                color = ThemeDS.colors.white,
                text = pokemonInfo.name,
                style = ThemeDS.typography.headingLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        if (pokemonInfo.description.isNotEmpty()) {
            item {
                SectionTitle(title = "Description")
                PText(
                    color = ThemeDS.colors.lightSea,
                    text = pokemonInfo.description,
                    style = ThemeDS.typography.label,
                    modifier = Modifier.padding(all = 8.dp)
                )
            }
        }
        if (pokemonInfo.types.isNotEmpty()) {
            item {
                SectionTitle(title = "Types")
                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    pokemonInfo.types.forEach { type ->
                        TypeChip(type = type.uppercase())
                    }
                }
            }
        }

        if (pokemonInfo.abilities.isNotEmpty()) {
            item {
                SectionTitle(title = "Abilities")
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    pokemonInfo.abilities.forEach { ability ->
                        PText(
                            color = ThemeDS.colors.lightSea,
                            text = ability,
                            style = ThemeDS.typography.label,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }

        if (pokemonInfo.moves.isNotEmpty()) {
            item {
                SectionTitle(title = "Moves")
                LazyRow(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(0.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    items(pokemonInfo.moves) { move ->
                        TypeChip(type = move.uppercase(), color = ThemeDS.colors.lightSea)
                    }
                }
            }
        }

        if (pokemonInfo.baseStats.isNotEmpty()) {
            item {
                SectionTitle(title = "Base Stats")
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    pokemonInfo.baseStats.forEach { (stat, value) ->
                        PText(
                            color = ThemeDS.colors.lightSea,
                            text = "${value.name.capitalize(Locale.current)} : $stat",
                            style = ThemeDS.typography.label,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }

        if (pokemonInfo.evolutionChain.isNotEmpty()) {
            item {
                SectionTitle(title = "Evolution Chain")
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    pokemonInfo.evolutionChain.forEach { evolution ->
                        PText(
                            color = ThemeDS.colors.lightSea,
                            text = "${evolution.name?.capitalize(Locale.current)} (Level ${evolution.minLevel} via ${evolution.trigger})",
                            style = ThemeDS.typography.label,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    PText(
        text = title,
        style = ThemeDS.typography.headingLarge,
        color = ThemeDS.colors.orange,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun TypeChip(type: String, color: Color = ThemeDS.colors.grey07) {
    Surface(
        shape = Shapes.medium,
        color = color,
        modifier = Modifier.padding(4.dp)
    ) {
        PText(
            text = type,
            style = ThemeDS.typography.label,
            color = ThemeDS.colors.grey02,
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Preview(device = "id:pixel_2")
@Composable
private fun PokemonDetailScreenPreview() {
    val samplePokemonInfo = PokemonInfo(
        name = "Pikachu",
        imageUrl = "",
        imageSVG = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/25.svg",
        types = listOf("Electric"),
        description = "Pikachu that can generate powerful electricity have cheek sacs that are extra soft and super stretchy.",
        abilities = listOf("Static", "Lightning Rod"),
        moves = listOf("Quick Attack", "Thunderbolt"),
        baseStats = listOf(),
        evolutionChain = listOf(
            PokemonInfo.EvolutionInfo("Pichu", null, "Friendship"),
            PokemonInfo.EvolutionInfo("Pikachu", null, "Thunder Stone"),
            PokemonInfo.EvolutionInfo("Raichu", null, "Thunder Stone")
        )
    )
    PokeStorieAppTheme {

        PokemonDetailScreen(pokemonInfo = samplePokemonInfo)
    }
}
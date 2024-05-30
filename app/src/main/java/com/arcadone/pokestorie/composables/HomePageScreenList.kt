package com.arcadone.pokestorie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcadone.pokestorie.domain.model.PokemonInfo
import com.arcadone.pokestorie.domain.model.PokemonListPreview
import com.arcadone.pokestorie.domain.model.PokemonLoader
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.ThemeDS

@Composable
fun HomePageScreenList(
    pokemonList: List<PokemonListPreview>,
    onClickCard: (PokemonInfo) -> Unit,
    onLoadMore: () -> Unit
) {
    val state = rememberLazyGridState()

    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.visibleItemsInfo.lastOrNull() }
            .collect { lastVisibleItem ->
                if (lastVisibleItem != null && lastVisibleItem.index == pokemonList.size - 1) {
                    onLoadMore()
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ThemeDS.colors.white),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            state = state,
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
        ) {
            items(pokemonList) { pokemon ->
                when (pokemon) {
                    is PokemonLoader -> {
                        Row(horizontalArrangement = Arrangement.Center) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(24.dp),
                                strokeWidth = 2.dp,
                                color = ThemeDS.colors.primary
                            )
                        }
                    }

                    is PokemonInfo -> {
                        PokemonCard(
                            modifier = Modifier.clickable { onClickCard(pokemon) },
                            imageSVG = pokemon.imageSVG,
                            pokemonName = pokemon.name,
                            pokemonDesc = pokemon.description,
                            pokemonTypes = pokemon.types
                        )
                    }
                }
            }
        }
    }
}

@Preview(device = "id:pixel_4")
@Composable
private fun SimplePreview() {
    val imageList = listOf(
        "https://picsum.photos/id/${(10)}/1200/1300",
        "https://picsum.photos/id/${(11)}/1200/1300",
        "https://picsum.photos/id/${(12)}/1200/1300",
    )
    var pokemonList: List<PokemonListPreview> = imageList.map {
        PokemonInfo(
            imageSVG = it,
            imageUrl = it,
            name = "Name",
            description = "Description",
            types = listOf("Grass", "Poison")
        )
    }
    pokemonList = pokemonList + PokemonLoader()
    PokeStorieAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomePageScreenList(pokemonList = pokemonList, onClickCard = {}, onLoadMore = {})
        }
    }
}
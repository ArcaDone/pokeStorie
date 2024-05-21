package com.arcadone.pokestorie.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arcadone.pokestorie.domain.model.PokemonInfo
import com.arcadone.pokestorie.domain.model.PokemonListPreview
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme


@Composable
fun SuccessContent(
    pokemonList: List<PokemonListPreview>,
    onClickCard: (PokemonInfo) -> Unit,
    onLoadMore: () -> Unit
) {
    HomePageScreenList(
        pokemonList = pokemonList,
        onClickCard = onClickCard,
        onLoadMore = onLoadMore
    )
}

@Preview(showBackground = true)
@Composable
fun SuccessContentPreview() {

    val pokemonList = listOf(
        PokemonInfo(name = "Bulbasaur", imageSVG = "https://example.com/bulbasaur.svg"),
        PokemonInfo(name = "Charmander", imageSVG = "https://example.com/charmander.svg")
    )
    PokeStorieAppTheme {
        SuccessContent(
            pokemonList = pokemonList,
            onClickCard = {},
            onLoadMore = {}
        )
    }
}

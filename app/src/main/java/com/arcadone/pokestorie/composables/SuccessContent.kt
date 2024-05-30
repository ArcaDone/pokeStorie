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
    val imageList = listOf(
        "https://picsum.photos/id/${(10)}/1200/1300",
        "https://picsum.photos/id/${(11)}/1200/1300",
        "https://picsum.photos/id/${(12)}/1200/1300",
        "https://picsum.photos/id/${(13)}/1200/1300",
        "https://picsum.photos/id/${(14)}/1200/1300",
        "https://picsum.photos/id/${(15)}/1200/1300",
        "https://picsum.photos/id/${(16)}/1200/1300",
        "https://picsum.photos/id/${(17)}/1200/1300",
        "https://picsum.photos/id/${(18)}/1200/1300",
        "https://picsum.photos/id/${(19)}/1200/1300",
        "https://picsum.photos/id/${(20)}/1200/1300",
        "https://picsum.photos/id/${(21)}/1200/1300",
        "https://picsum.photos/id/${(22)}/1200/1300",
        "https://picsum.photos/id/${(23)}/1200/1300",
    )
    val pokemonList = imageList.map {
        PokemonInfo(imageSVG = it, imageUrl = it, name = "Name", description = "Description", types = listOf("Grass", "Poison"))
    }
    PokeStorieAppTheme {
        SuccessContent(
            pokemonList = pokemonList,
            onClickCard = {},
            onLoadMore = {}
        )
    }
}

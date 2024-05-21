package com.arcadone.pokestorie.composables

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcadone.pokestorie.domain.model.PokemonInfo
import com.arcadone.pokestorie.domain.model.PokemonListPreview
import com.arcadone.pokestorie.domain.model.PokemonLoader
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.ThemeDS
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomRow(
    listState: LazyListState,
    pokemonList: List<PokemonListPreview>,
    pagerState: PagerState,
    scope: CoroutineScope
) {
    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp)
            .background(ThemeDS.colors.darkBlue),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(pokemonList) { index, pokemon ->
            val isSelected = pagerState.currentPage == index

            val transition2 = updateTransition(isSelected, label = "selected")
            val padding2 by transition2.animateDp(label = "padding") { stateSelected ->
                if (stateSelected) 2.dp else 8.dp
            }
            val roundedCornerShape2 by transition2.animateDp(label = "corner") { stateSelected ->
                if (stateSelected) 4.dp else 8.dp
            }
            val size by transition2.animateDp(label = "corner") { stateSelected ->
                if (stateSelected) 100.dp else 90.dp
            }
            val color by transition2.animateColor(label = "color") { stateSelected ->
                if (stateSelected) ThemeDS.colors.mustard else ThemeDS.colors.grey06
            }
            when (pokemon) {
                is PokemonLoader -> {
                    SquaredLoaderBox()
                }

                is PokemonInfo -> {
                    PokemonPreview(
                        modifier = Modifier
                            .padding(all = padding2)
                            .size(size)
                            .clip(RoundedCornerShape(roundedCornerShape2))
                            .background(color)
                            .clickable {
                                scope.launch {
                                    pagerState.scrollToPage(index)
                                    val centerIndex =
                                        (listState.layoutInfo.visibleItemsInfo.size / 2)
                                    listState.animateScrollToItem(
                                        index.coerceAtLeast(centerIndex + 1) - centerIndex
                                    )
                                }
                            },
                        imageSVG = pokemon.imageSVG,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun BottomRowPreview() {
    val pokemonList = listOf(
        PokemonInfo(name = "Bulbasaur", imageSVG = "https://example.com/bulbasaur.svg"),
        PokemonInfo(name = "Charmander", imageSVG = "https://example.com/charmander.svg"),
        PokemonLoader()
    )
    val pagerState = rememberPagerState()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    PokeStorieAppTheme {
        BottomRow(
            listState = listState,
            pokemonList = pokemonList,
            pagerState = pagerState,
            scope = scope
        )
    }
}

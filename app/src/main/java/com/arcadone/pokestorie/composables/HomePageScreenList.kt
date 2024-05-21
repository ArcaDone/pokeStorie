package com.arcadone.pokestorie.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.arcadone.pokestorie.R
import com.arcadone.pokestorie.domain.model.PokemonInfo
import com.arcadone.pokestorie.domain.model.PokemonListPreview
import com.arcadone.pokestorie.domain.model.PokemonLoader
import com.arcadone.pokestorie.ui.theme.PokeStorieAppTheme
import com.arcadone.pokestorie.ui.theme.largeRadialGradient
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePageScreenList(
    pokemonList: List<PokemonListPreview>,
    onClickCard: (PokemonInfo) -> Unit,
    onLoadMore: () -> Unit
) {
    val haptic = LocalHapticFeedback.current
    val pagerState = rememberPagerState()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            count = pokemonList.size,
            contentPadding = PaddingValues(horizontal = 64.dp),
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) { page ->
            val pageOffset =
                (pagerState.currentPage - page + pagerState.currentPageOffset).absoluteValue

            when (val pokemon = pokemonList[page]) {
                is PokemonLoader -> {
                    PokemonCard(
                        placeHolder = R.drawable.present,
                        pokemonName = "Satispay Test",
                        modifier = Modifier.applyGraphicsLayer(pageOffset),
                    )
                }

                is PokemonInfo -> {
                    PokemonCard(
                        modifier = Modifier
                            .clickable { onClickCard(pokemon) }
                            .applyGraphicsLayer(pageOffset),
                        imageSVG = pokemon.imageSVG,
                        pokemonName = pokemon.name
                    )
                }
            }
        }

        BottomRow(
            listState = listState,
            pokemonList = pokemonList,
            pagerState = pagerState,
            scope = scope
        )
    }

    LaunchedEffect(pagerState.currentPage) {
        val centerIndex = listState.layoutInfo.visibleItemsInfo.size / 2
        val targetIndex = pagerState.currentPage - centerIndex
        listState.animateScrollToItem(targetIndex.coerceAtLeast(0))
    }

    LaunchedEffect(pokemonList) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { firstVisibleItemIndex ->
                val visibleItems = listState.layoutInfo.visibleItemsInfo
                if (visibleItems.isNotEmpty()) {
                    val centerIndex = visibleItems.size / 2
                    val currentIndex = firstVisibleItemIndex + centerIndex
                    if (currentIndex in pokemonList.indices && pagerState.currentPage != currentIndex) {
                        scope.launch {
                            pagerState.animateScrollToPage(currentIndex)
                        }
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    }
                }

                val lastVisibleItemIndex = firstVisibleItemIndex + visibleItems.size
                if (lastVisibleItemIndex >= pokemonList.size) {
                    onLoadMore()
                }
            }
    }
}

fun Modifier.applyGraphicsLayer(pageOffset: Float): Modifier {
    return this.graphicsLayer {
        alpha = lerp(
            start = 0.5f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )
        scaleX = lerp(
            start = 0.85f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )
        scaleY = lerp(
            start = 0.85f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )
    }
}

@Preview(device = "id:pixel_4")
@Composable
private fun SimplePreview() {
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
        PokemonInfo(imageSVG = it, imageUrl = it)
    }
    PokeStorieAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomePageScreenList(pokemonList = pokemonList, onClickCard = {}, onLoadMore = {})
        }
    }
}
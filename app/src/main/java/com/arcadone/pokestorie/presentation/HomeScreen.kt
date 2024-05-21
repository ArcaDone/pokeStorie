package com.arcadone.pokestorie.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.arcadone.pokestorie.composables.ErrorContent
import com.arcadone.pokestorie.composables.LoadingContent
import com.arcadone.pokestorie.composables.ModalBottomSheetDialog
import com.arcadone.pokestorie.composables.OverlayLoader
import com.arcadone.pokestorie.composables.SearchingBar
import com.arcadone.pokestorie.composables.SuccessContent
import com.arcadone.pokestorie.ui.theme.largeRadialGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState
    val pokemonList by viewModel.pokemonList
    val pokemonBySearch by viewModel.pokemonBySearch
    val pokemonDetails by viewModel.pokemonDetails
    val isSearching by viewModel.isSearching
    val searchError by viewModel.searchError

    var pokemonDetailBottomSheetVisible by remember { mutableStateOf(false) }
    var pokemonBySearchDetailBottomSheetVisible by remember { mutableStateOf(false) }

    LaunchedEffect(pokemonBySearch) {
        if (pokemonBySearch != null && pokemonBySearch?.name?.isNotEmpty() == true) {
            pokemonBySearchDetailBottomSheetVisible = true
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = { SearchingBar(onClickSearch = viewModel::searchPokemon) },
        content = { paddingValues ->
            if (pokemonDetailBottomSheetVisible) {
                ModalBottomSheetDialog(
                    pokemonInfo = pokemonDetails ?: return@Scaffold,
                    onDismissRequest = { pokemonDetailBottomSheetVisible = false }
                )
            }
            if (pokemonBySearchDetailBottomSheetVisible) {
                pokemonBySearch?.let {
                    ModalBottomSheetDialog(
                        pokemonInfo = it,
                        onDismissRequest = {
                            viewModel.cleanSearch()
                            pokemonBySearchDetailBottomSheetVisible = false
                        }
                    )
                }
            }

            Box(
                Modifier
                    .fillMaxSize()
                    .background(largeRadialGradient)
                    .padding(paddingValues)
            ) {
                when (uiState) {
                    is HomeUiState.Loading -> LoadingContent()
                    is HomeUiState.ShowSuccess -> SuccessContent(
                        pokemonList = pokemonList,
                        onClickCard = {
                            viewModel.openModalBottomSheet(it)
                            pokemonDetailBottomSheetVisible = true
                        },
                        onLoadMore = { viewModel.getPokemonByUrlList() }
                    )
                    is HomeUiState.ShowError -> ErrorContent(onRetry = viewModel::retry)
                }
            }
        }
    )

    if (isSearching) {
        OverlayLoader()
    } else if (searchError != null) {
        Toast.makeText(context, searchError, Toast.LENGTH_SHORT).show()
    }
}

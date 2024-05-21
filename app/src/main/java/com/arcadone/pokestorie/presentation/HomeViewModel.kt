package com.arcadone.pokestorie.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcadone.pokestorie.domain.model.PokemonInfo
import com.arcadone.pokestorie.domain.model.PokemonListPreview
import com.arcadone.pokestorie.domain.model.PokemonLoader
import com.arcadone.pokestorie.domain.usecase.GetPokemonListByUrlUseCase
import com.arcadone.pokestorie.domain.usecase.GetPokemonListUseCase
import com.arcadone.pokestorie.domain.usecase.GetSinglePokemonUseCase
import com.arcadone.pokestorie.domain.model.PokeUseCaseData
import com.arcadone.pokestorie.domain.model.PokemonData
import com.arcadone.pokestorie.networkconnection.NetworkConnection
import com.arcadone.pokestorie.rest.model.ApiResult
import com.arcadone.pokestorie.rest.model.onFailure
import com.arcadone.pokestorie.rest.model.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonListUseCase: GetPokemonListUseCase,
    private val pokemonListByUrlUseCase: GetPokemonListByUrlUseCase,
    private val getSinglePokemonUseCase: GetSinglePokemonUseCase,
    private val networkConnection: NetworkConnection
) : ViewModel() {

    private var nextPage: String = ""
    private var isLoadingNextPage = false

    private val _pokemonDetails = mutableStateOf<PokemonInfo?>(null)
    val pokemonDetails: State<PokemonInfo?> = _pokemonDetails

    private val _pokemonBySearch = mutableStateOf<PokemonInfo?>(null)
    val pokemonBySearch: State<PokemonInfo?> = _pokemonBySearch

    private val _pokemonList = mutableStateOf<List<PokemonListPreview>>(listOf())
    val pokemonList: State<List<PokemonListPreview>> = _pokemonList

    private val _uiState = mutableStateOf<HomeUiState>(HomeUiState.Loading(networkConnection.isOnline()))
    val uiState: State<HomeUiState> = _uiState

    private val _isSearching = mutableStateOf(false)
    val isSearching: State<Boolean> = _isSearching

    private val _searchError = mutableStateOf<String?>(null)
    val searchError: State<String?> = _searchError

    init {
        getPokemonList(PokeUseCaseData())
    }

    private fun getPokemonList(params: PokeUseCaseData) {
        _uiState.value = HomeUiState.Loading(networkConnection.isOnline())
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, exception ->
            Log.d(HomeViewModel::class.java.simpleName, "Exception: ${exception.stackTrace}")
        }) {
            handleResult(pokemonListUseCase.invoke(params))
        }
    }

    fun getPokemonByUrlList() {
        if (isLoadingNextPage) return

        isLoadingNextPage = true
        updateListWithLoader(true)

        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, exception ->
            Log.d(HomeViewModel::class.java.simpleName, "Exception: ${exception.stackTrace}")
        }) {
            handleResult(pokemonListByUrlUseCase.invoke(nextPage), append = true)
            updateListWithLoader(false)
        }
    }

    private fun handleResult(result: ApiResult<PokemonData>, append: Boolean = false) {
        result.onSuccess { pokemonData ->
            if (append) {
                _pokemonList.value = _pokemonList.value.filter { it !is PokemonLoader } + pokemonData.pokemonInfo
            } else {
                _pokemonList.value = pokemonData.pokemonInfo
            }
            nextPage = pokemonData.next
            _uiState.value = HomeUiState.ShowSuccess(Unit)
        }.onFailure {
            _uiState.value = HomeUiState.ShowError(it.message ?: "An Error Occurred")
        }.also {
            isLoadingNextPage = false
        }
    }

    private fun updateListWithLoader(showLoader: Boolean) {
        val currentList = _pokemonList.value.toMutableList()
        if (showLoader) {
            currentList.add(PokemonLoader())
        } else {
            currentList.removeAll { it is PokemonLoader }
        }
        _pokemonList.value = currentList
    }

    fun openModalBottomSheet(pokemonDetails: PokemonInfo) {
        _pokemonDetails.value = pokemonDetails
    }

    fun searchPokemon(query: String) {
        if (query.isEmpty()) return

        _isSearching.value = true
        _searchError.value = null
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, exception ->
            Log.d(HomeViewModel::class.java.simpleName, "Exception: ${exception.stackTrace}")
        }) {
            getSinglePokemonUseCase.invoke(query).onSuccess {
                _pokemonBySearch.value = it
                _isSearching.value = false
            }.onFailure {
                _searchError.value = it.message
                _isSearching.value = false
            }
        }
    }

    fun cleanSearch() {
        _pokemonBySearch.value = null
    }

    fun retry() {
        getPokemonList(PokeUseCaseData())
    }
}

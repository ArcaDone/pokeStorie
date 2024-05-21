package com.arcadone.pokestorie.presentation

sealed class HomeUiState {
    data class Loading(val isConnected: Boolean = false) : HomeUiState()
    data class ShowSuccess(val pokemonList: Unit) : HomeUiState()
    data class ShowError(val errorMessage: String) : HomeUiState()
}
package com.arcadone.pokestorie.domain.model

data class PokemonList(val next: String,val results: List<Pokemon> = listOf()) {
    data class Pokemon(val name: String, val url: String)
}

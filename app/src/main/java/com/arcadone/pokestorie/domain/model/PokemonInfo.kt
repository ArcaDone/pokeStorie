package com.arcadone.pokestorie.domain.model

import com.arcadone.pokestorie.R

sealed class PokemonListPreview

data class PokemonInfo(
    val name: String = "",
    val imageUrl: String = "",
    val imageSVG: String? = "",
    val types: List<String> = listOf(),
    val description: String = "",
    val abilities: List<String> = listOf(),
    val moves: List<String> = listOf(),
    val baseStats: List<PokemonDetails.Stat> = listOf(),
    val evolutionChain: List<EvolutionInfo> = listOf()
) : PokemonListPreview() {

    data class EvolutionInfo(
        val name: String?,
        val minLevel: Int?,
        val trigger: String
    )
}

data class PokemonLoader(val res: Int = R.drawable.loader) : PokemonListPreview()

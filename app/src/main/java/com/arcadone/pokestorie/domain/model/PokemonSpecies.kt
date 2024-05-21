package com.arcadone.pokestorie.domain.model

data class PokemonSpecies(
    val evolutionChainUrl: String,
    val flavorTextEntries: List<FlavorTextEntry>
) {
    data class FlavorTextEntry(val flavorText: String, val language: Language)
    data class Language(val name: String)
}
data class EvolutionChainLink(
    val species: Species,
    val evolvesTo: List<EvolutionChainLink>?,
    val evolutionDetails: List<EvolutionDetail>?
)

data class Species(val name: String?)
data class EvolutionDetail(val minLevel: Int?, val trigger: Trigger)
data class Trigger(val name: String)


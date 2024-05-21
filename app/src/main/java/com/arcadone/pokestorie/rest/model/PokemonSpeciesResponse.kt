package com.arcadone.pokestorie.rest.model

import com.arcadone.pokestorie.domain.model.PokemonSpecies
import com.fasterxml.jackson.annotation.JsonProperty

data class PokemonSpeciesResponse(
    @JsonProperty("evolution_chain")
    val evolutionChain: EvolutionChainRes,
    @JsonProperty("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryRes>
) {
    data class EvolutionChainRes(@JsonProperty("url") val url: String)
    data class FlavorTextEntryRes(
        @JsonProperty("flavor_text")
        val flavorText: String,
        @JsonProperty("language")
        val language: LanguageRes
    )

    data class LanguageRes(
        @JsonProperty("name")
        val name: String
    )

    fun toDomain(): PokemonSpecies {
        return PokemonSpecies(
            evolutionChainUrl = evolutionChain.url,
            flavorTextEntries = flavorTextEntries.map {
                PokemonSpecies.FlavorTextEntry(
                    flavorText = it.flavorText,
                    language = PokemonSpecies.Language(it.language.name)
                )
            }
        )
    }
}

data class EvolutionChainLinkRes(
    @JsonProperty("species")
    val species: SpeciesRes?,
    @JsonProperty("evolves_to")
    val evolvesTo: List<EvolutionChainLinkRes>?,
    @JsonProperty("evolution_details")
    val evolutionDetails: List<EvolutionDetailRes>?
)

data class SpeciesRes(@JsonProperty("name") val name: String)

data class EvolutionDetailRes(
    @JsonProperty("min_level") val minLevel: Int?,
    @JsonProperty("trigger") val trigger: TriggerRes
)

data class TriggerRes(@JsonProperty("name") val name: String)


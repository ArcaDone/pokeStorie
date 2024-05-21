package com.arcadone.pokestorie.rest.model

import com.arcadone.pokestorie.domain.model.PokemonList
import com.fasterxml.jackson.annotation.JsonProperty

data class PokemonListResponse(
    @JsonProperty("next")
    val next: String,
    @JsonProperty("results")
    val results: List<PokemonResponse>
) {
    data class PokemonResponse(
        @JsonProperty("name")
        val name: String,
        @JsonProperty("url")
        val url: String
    )

    fun toDomain(): PokemonList {
        return PokemonList(
            next = next,
            results = results.map {
                PokemonList.Pokemon(
                    it.name,
                    it.url
                )
            })
    }
}


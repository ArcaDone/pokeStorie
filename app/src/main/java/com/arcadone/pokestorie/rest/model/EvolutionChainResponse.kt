package com.arcadone.pokestorie.rest.model

import com.arcadone.pokestorie.domain.model.EvolutionChain
import com.fasterxml.jackson.annotation.JsonProperty

data class EvolutionChainResponse(
    @JsonProperty("chain")
    val chain: EvolutionChainLinkRes
) {
    fun toDomain() = EvolutionChain(chain.toDomain())
}
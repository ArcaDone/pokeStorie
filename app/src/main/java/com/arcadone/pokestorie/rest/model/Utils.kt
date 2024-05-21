package com.arcadone.pokestorie.rest.model

import com.arcadone.pokestorie.domain.model.EvolutionChainLink
import com.arcadone.pokestorie.domain.model.EvolutionDetail
import com.arcadone.pokestorie.domain.model.Species
import com.arcadone.pokestorie.domain.model.Trigger

fun EvolutionChainLinkRes.toDomain(): EvolutionChainLink {
    return EvolutionChainLink(
        species = Species(species?.name),
        evolvesTo = evolvesTo?.map { it.toDomain() },
        evolutionDetails = evolutionDetails?.map { detail ->
            EvolutionDetail(
                minLevel = detail.minLevel,
                trigger = Trigger(detail.trigger.name)
            )
        }
    )
}
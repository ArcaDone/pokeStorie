package com.arcadone.pokestorie.rest.model

import com.arcadone.pokestorie.domain.model.PokemonDetails
import com.fasterxml.jackson.annotation.JsonProperty

data class PokemonDetailsResponse(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("sprites")
    val spritesRes: SpritesRes,
    @JsonProperty("types")
    val typeRes: List<TypeRes>,
    @JsonProperty("abilities")
    val abilities: List<AbilityRes>,
    @JsonProperty("moves")
    val moves: List<MoveRes>,
    @JsonProperty("stats")
    val stats: List<StatRes>,
) {

    data class SpritesRes(
        @JsonProperty("front_default")
        val frontDefault: String,
        @JsonProperty("other")
        val otherRes: OtherRes?
    )

    data class OtherRes(
        @JsonProperty("dream_world")
        val dreamWorldRes: DreamWorldRes?
    )

    data class DreamWorldRes(
        @JsonProperty("front_default")
        val frontDefault: String?
    )

    data class TypeRes(
        @JsonProperty("type")
        val type: TypeDetailRes
    )

    data class TypeDetailRes(
        @JsonProperty("name")
        val name: String
    )

    data class AbilityRes(@JsonProperty("ability") val ability: AbilityInfo)
    data class AbilityInfo(@JsonProperty("name") val name: String)

    data class MoveRes(@JsonProperty("move") val move: MoveInfo)
    data class MoveInfo(@JsonProperty("name") val name: String)

    data class StatRes(
        @JsonProperty("base_stat") val baseStat: Int,
        @JsonProperty("stat") val stat: StatInfo
    )

    data class StatInfo(@JsonProperty("name") val name: String)

    fun toDomain(): PokemonDetails {
        return PokemonDetails(
            id = id,
            name = name,
            sprites = PokemonDetails.Sprites(
                frontDefault = spritesRes.frontDefault,
                other = PokemonDetails.Other(
                    dreamWorld = PokemonDetails.DreamWorld(
                        frontDefault = spritesRes.otherRes?.dreamWorldRes?.frontDefault
                    )
                )
            ),
            types = typeRes.map {
                PokemonDetails.Type(
                    type = PokemonDetails.TypeDetail(it.type.name)
                )
            },
            abilities = abilities.map {
                PokemonDetails.Ability(
                    PokemonDetails.AbilityInfo(it.ability.name)
                )
            },
            moves = moves.map { PokemonDetails.Move(PokemonDetails.MoveInfo(it.move.name)) },
            stats = stats.map {
                PokemonDetails.Stat(
                    baseStat = it.baseStat,
                    stat = PokemonDetails.StatInfo(it.stat.name)
                )
            }
        )
    }
}
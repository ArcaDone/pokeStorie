package com.arcadone.pokestorie.domain.model

data class PokemonDetails(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val abilities: List<Ability>,
    val moves: List<Move>,
    val stats: List<Stat>,
) {

    data class Sprites(val frontDefault: String, val other: Other?)

    data class Other(val dreamWorld: DreamWorld?)

    data class DreamWorld(val frontDefault: String?)

    data class Type(val type: TypeDetail)

    data class TypeDetail(val name: String)

    data class Ability(val ability: AbilityInfo)
    data class AbilityInfo(val name: String)

    data class Move(val move: MoveInfo)
    data class MoveInfo(val name: String)

    data class Stat(val baseStat: Int, val stat: StatInfo)
    data class StatInfo(val name: String)

}
package com.arcadone.pokestorie.domain.repository

import com.arcadone.pokestorie.domain.model.PokemonInfo
import com.arcadone.pokestorie.domain.model.EvolutionChain
import com.arcadone.pokestorie.domain.model.EvolutionChainLink
import com.arcadone.pokestorie.domain.model.PokemonDetails
import com.arcadone.pokestorie.domain.model.PokemonList
import com.arcadone.pokestorie.domain.model.PokemonSpecies
import com.arcadone.pokestorie.rest.PokeApiService
import com.arcadone.pokestorie.rest.model.EvolutionChainResponse
import com.arcadone.pokestorie.rest.model.PokemonDetailsResponse
import com.arcadone.pokestorie.rest.model.PokemonListResponse
import com.arcadone.pokestorie.rest.model.PokemonSpeciesResponse
import retrofit2.Response

interface PokeAPIRepositoryInterface {
    suspend fun getPokemonList(offset: Int = 0, limit: Int = 20): PokemonList?
    suspend fun getPokemonDetails(name: String): PokemonDetails?
    suspend fun getPokemonSpecies(name: String): PokemonSpecies?
    suspend fun getPokemonListByUrl(url: String): PokemonList?
    suspend fun getEvolutionChain(id: String): EvolutionChain?
    suspend fun fetchPokemonDetails(pokemonName: String): PokemonInfo?
}

class PokeAPIRepositoryImpl(
    private val pokeApiService: PokeApiService,
) : PokeAPIRepositoryInterface {

    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonList? {
        val response: Response<PokemonListResponse> = pokeApiService.getPokemonList(offset, limit)
        return response.body()?.toDomain()
    }

    override suspend fun getPokemonDetails(name: String): PokemonDetails? {
        val response: Response<PokemonDetailsResponse> = pokeApiService.getPokemonDetails(name)
        return response.body()?.toDomain()
    }

    override suspend fun getPokemonSpecies(name: String): PokemonSpecies? {
        val response: Response<PokemonSpeciesResponse> = pokeApiService.getPokemonSpecies(name)
        return response.body()?.toDomain()
    }

    override suspend fun getPokemonListByUrl(url: String): PokemonList? {
        val response: Response<PokemonListResponse> = pokeApiService.getPokemonListByUrl(url)
        return response.body()?.toDomain()
    }

    override suspend fun getEvolutionChain(id: String): EvolutionChain? {
        val response: Response<EvolutionChainResponse> = pokeApiService.getEvolutionChain(id)
        return response.body()?.toDomain()
    }

    override suspend fun fetchPokemonDetails(pokemonName: String): PokemonInfo? {
        val details = getPokemonDetails(pokemonName)
        val species = getPokemonSpecies(pokemonName)
        val description = species?.flavorTextEntries?.firstOrNull { it.language.name == "en" }?.flavorText
            ?: "No description available"
        val moves = details?.moves?.map { it.move.name } ?: listOf()
        val evolutionChain = species?.evolutionChainUrl?.let { url ->
            fetchEvolutionChain(url)
        } ?: listOf()

        return details?.let {
            PokemonInfo(
                name = details.name,
                imageUrl = details.sprites.frontDefault,
                imageSVG = details.sprites.other?.dreamWorld?.frontDefault,
                types = details.types.map { it.type.name },
                description = description,
                abilities = details.abilities.map { it.ability.name },
                moves = moves,
                baseStats = details.stats,
                evolutionChain = evolutionChain
            )
        }
    }

    private suspend fun fetchEvolutionChain(url: String): List<PokemonInfo.EvolutionInfo> {
        val id = url.split("/").dropLast(1).last()
        val evolutionChainResponse = getEvolutionChain(id)
        return evolutionChainResponse?.let {
            parseEvolutionChain(it.chain)
        } ?: listOf()
    }

    private fun parseEvolutionChain(chain: EvolutionChainLink): List<PokemonInfo.EvolutionInfo> {
        val evolutions = mutableListOf<PokemonInfo.EvolutionInfo>()
        evolutions.add(
            PokemonInfo.EvolutionInfo(
                name = chain.species.name,
                minLevel = chain.evolutionDetails?.firstOrNull()?.minLevel,
                trigger = chain.evolutionDetails?.firstOrNull()?.trigger?.name ?: "Unknown"
            )
        )
        chain.evolvesTo?.forEach { evolvesTo ->
            evolutions.addAll(parseEvolutionChain(evolvesTo))
        }
        return evolutions
    }
}

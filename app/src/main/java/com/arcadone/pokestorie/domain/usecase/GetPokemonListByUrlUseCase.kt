package com.arcadone.pokestorie.domain.usecase

import com.arcadone.pokestorie.domain.model.PokemonData
import com.arcadone.pokestorie.domain.repository.PokeAPIRepositoryInterface
import com.arcadone.pokestorie.rest.model.ApiResult
import javax.inject.Inject

interface GetPokemonListByUrlUseCase {
    suspend fun invoke(params: String): ApiResult<PokemonData>
}

class GetPokemonListByUrlUseCaseImpl @Inject constructor(
    private val apiRepository: PokeAPIRepositoryInterface
) : RestUseCase<String, PokemonData>(), GetPokemonListByUrlUseCase {
    override suspend fun execute(params: String): ApiResult<PokemonData> {
        val response = apiRepository.getPokemonListByUrl(params)
        return if (response != null) {
            val pokemonInfoList = response.results.mapNotNull { pokemon ->
                apiRepository.fetchPokemonDetails(pokemon.name)
            }
            ApiResult.success(PokemonData(response.next, pokemonInfoList))
        } else {
            ApiResult.error(Throwable("No results found"))
        }
    }
}

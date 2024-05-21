package com.arcadone.pokestorie.domain.usecase

import com.arcadone.pokestorie.domain.model.PokeUseCaseData
import com.arcadone.pokestorie.domain.model.PokemonData
import com.arcadone.pokestorie.domain.repository.PokeAPIRepositoryInterface
import com.arcadone.pokestorie.rest.model.ApiResult
import javax.inject.Inject

interface GetPokemonListUseCase {
    suspend fun invoke(params: PokeUseCaseData): ApiResult<PokemonData>
}

class GetPokemonListUseCaseImpl @Inject constructor(
    private val apiRepository: PokeAPIRepositoryInterface
) : RestUseCase<PokeUseCaseData, PokemonData>(), GetPokemonListUseCase {
    override suspend fun execute(params: PokeUseCaseData): ApiResult<PokemonData> {
        val response = apiRepository.getPokemonList(offset = params.offset, limit = params.limit)
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

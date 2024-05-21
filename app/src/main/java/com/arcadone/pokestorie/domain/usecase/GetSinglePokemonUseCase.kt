package com.arcadone.pokestorie.domain.usecase

import com.arcadone.pokestorie.domain.model.PokemonInfo
import com.arcadone.pokestorie.domain.repository.PokeAPIRepositoryInterface
import com.arcadone.pokestorie.rest.model.ApiResult
import javax.inject.Inject

interface GetSinglePokemonUseCase {
    suspend fun invoke(params: String): ApiResult<PokemonInfo>
}

class GetSinglePokemonUseCaseImpl @Inject constructor(
    private val apiRepository: PokeAPIRepositoryInterface
) : RestUseCase<String, PokemonInfo>(), GetSinglePokemonUseCase {
    override suspend fun execute(params: String): ApiResult<PokemonInfo> {
        return apiRepository.fetchPokemonDetails(params)?.let {
            ApiResult.success(it)
        } ?: ApiResult.error(Exception("Pokemon not found"))
    }
}


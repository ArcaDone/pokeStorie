package com.arcadone.pokestorie.rest

import com.arcadone.pokestorie.rest.model.EvolutionChainResponse
import com.arcadone.pokestorie.rest.model.PokemonDetailsResponse
import com.arcadone.pokestorie.rest.model.PokemonListResponse
import com.arcadone.pokestorie.rest.model.PokemonSpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): Response<PokemonDetailsResponse>

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(@Path("name") name: String): Response<PokemonSpeciesResponse>

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(@Path("id") id: String): Response<EvolutionChainResponse>

    @GET
    suspend fun getPokemonListByUrl(@Url url: String): Response<PokemonListResponse>
}

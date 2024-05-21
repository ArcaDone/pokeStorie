package com.arcadone.pokestorie

import com.arcadone.pokestorie.domain.model.PokemonDetails
import com.arcadone.pokestorie.domain.model.PokemonInfo
import com.arcadone.pokestorie.domain.repository.PokeAPIRepositoryImpl
import com.arcadone.pokestorie.rest.PokeApiService
import com.arcadone.pokestorie.rest.model.EvolutionChainLinkRes
import com.arcadone.pokestorie.rest.model.EvolutionChainResponse
import com.arcadone.pokestorie.rest.model.PokemonDetailsResponse
import com.arcadone.pokestorie.rest.model.PokemonListResponse
import com.arcadone.pokestorie.rest.model.PokemonSpeciesResponse
import com.arcadone.pokestorie.rest.model.SpeciesRes
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

class PokeAPIRepositoryImplTest {

    private lateinit var pokeApiService: PokeApiService
    private lateinit var pokeAPIRepository: PokeAPIRepositoryImpl
    private val mockedPokemonDetailsResponse = PokemonDetailsResponse(
        id = 1,
        name = "Pikachu",
        spritesRes = PokemonDetailsResponse.SpritesRes(
            frontDefault = "frontDefaultUrl",
            otherRes = PokemonDetailsResponse.OtherRes(
                dreamWorldRes = PokemonDetailsResponse.DreamWorldRes(
                    frontDefault = "dreamWorldFrontDefaultUrl"
                )
            )
        ),
        typeRes = listOf(PokemonDetailsResponse.TypeRes(PokemonDetailsResponse.TypeDetailRes("Electric"))),
        abilities = listOf(PokemonDetailsResponse.AbilityRes(PokemonDetailsResponse.AbilityInfo("Static"))),
        moves = listOf(PokemonDetailsResponse.MoveRes(PokemonDetailsResponse.MoveInfo("Thunderbolt"))),
        stats = listOf(PokemonDetailsResponse.StatRes(100, PokemonDetailsResponse.StatInfo("Speed")))
    )

    private val mockedPokemonSpeciesResponse = PokemonSpeciesResponse(
        evolutionChain = PokemonSpeciesResponse.EvolutionChainRes("evolutionChainUrl"),
        flavorTextEntries = listOf(
            PokemonSpeciesResponse.FlavorTextEntryRes("Pikachu is an Electric-type Pokémon.", PokemonSpeciesResponse.LanguageRes("en"))
        )
    )

    private val mockedPokemonListResponseByUrl = PokemonListResponse(
        next = "nextUrl",
        results = listOf(
            PokemonListResponse.PokemonResponse("pokemon1", "url1"),
            PokemonListResponse.PokemonResponse("pokemon2", "url2"),
            PokemonListResponse.PokemonResponse("pokemon3", "url3")
        )
    )

    private val mockedEvolutionChainResponse = EvolutionChainResponse(
        chain = EvolutionChainLinkRes(
            species = SpeciesRes("pokemon"),
            evolvesTo = null,
            evolutionDetails = null
        )
    )

    private val expectedPokemonInfo = PokemonInfo(
        name = "Pikachu",
        imageUrl = "frontDefaultUrl",
        imageSVG = "dreamWorldFrontDefaultUrl",
        types = listOf("Electric"),
        description = "Pikachu is an Electric-type Pokémon.",
        abilities = listOf("Static"),
        moves = listOf("Thunderbolt"),
        baseStats = listOf(PokemonDetails.Stat(100, PokemonDetails.StatInfo("Speed"))),
        evolutionChain = listOf(PokemonInfo.EvolutionInfo(name = "pokemon", minLevel = null, trigger = "Unknown"))
    )

    @Before
    fun setup() {
        pokeApiService = mock(PokeApiService::class.java)
        pokeAPIRepository = PokeAPIRepositoryImpl(pokeApiService)
    }

    @Test
    fun `test getPokemonList`() = runBlocking {
        val mockedPokemonResponse1 = PokemonListResponse.PokemonResponse("pokemon1", "url1")
        val mockedPokemonResponse2 = PokemonListResponse.PokemonResponse("pokemon2", "url2")
        val mockedPokemonResponse3 = PokemonListResponse.PokemonResponse("pokemon3", "url3")

        val mockedPokemonListResponse = PokemonListResponse(
            next = "nextUrl",
            results = listOf(
                mockedPokemonResponse1,
                mockedPokemonResponse2,
                mockedPokemonResponse3
            )
        )

        val expectedPokemonList = mockedPokemonListResponse.toDomain()
        val response = Response.success(mockedPokemonListResponse)

        `when`(pokeApiService.getPokemonList(0, 20)).thenReturn(response)

        val actualPokemonList = pokeAPIRepository.getPokemonList(0, 20)

        assertEquals(expectedPokemonList, actualPokemonList)
    }
    @Test
    fun `test getPokemonDetails`() = runBlocking {
        val pokemonDetailsResponse = mockedPokemonDetailsResponse
        val expectedPokemonDetails = pokemonDetailsResponse.toDomain()
        val response = Response.success(pokemonDetailsResponse)

        `when`(pokeApiService.getPokemonDetails("pokemonName")).thenReturn(response)

        val actualPokemonDetails = pokeAPIRepository.getPokemonDetails("pokemonName")

        assertEquals(expectedPokemonDetails, actualPokemonDetails)
    }

    @Test
    fun `test getPokemonSpecies`() = runBlocking {
        val pokemonSpeciesResponse = mockedPokemonSpeciesResponse
        val expectedPokemonSpecies = pokemonSpeciesResponse.toDomain()
        val response = Response.success(pokemonSpeciesResponse)

        `when`(pokeApiService.getPokemonSpecies("pokemonName")).thenReturn(response)

        val actualPokemonSpecies = pokeAPIRepository.getPokemonSpecies("pokemonName")

        assertEquals(expectedPokemonSpecies, actualPokemonSpecies)
    }

    @Test
    fun `test getPokemonListByUrl`() = runBlocking {
        val pokemonListResponse = mockedPokemonListResponseByUrl
        val expectedPokemonList = pokemonListResponse.toDomain()
        val response = Response.success(pokemonListResponse)

        `when`(pokeApiService.getPokemonListByUrl("url")).thenReturn(response)

        val actualPokemonList = pokeAPIRepository.getPokemonListByUrl("url")

        assertEquals(expectedPokemonList, actualPokemonList)
    }

    @Test
    fun `test getEvolutionChain`() = runBlocking {
        val evolutionChainResponse = mockedEvolutionChainResponse
        val expectedEvolutionChain = evolutionChainResponse.toDomain()
        val response = Response.success(evolutionChainResponse)

        `when`(pokeApiService.getEvolutionChain("id")).thenReturn(response)

        val actualEvolutionChain = pokeAPIRepository.getEvolutionChain("id")

        assertEquals(expectedEvolutionChain, actualEvolutionChain)
    }

    @Test
    fun `test fetchPokemonDetails`() = runBlocking {
        val pokemonDetailsResponse = mockedPokemonDetailsResponse
        val pokemonSpeciesResponse = mockedPokemonSpeciesResponse.copy(evolutionChain = PokemonSpeciesResponse.EvolutionChainRes("evolutionChainUrl/1"))
        val expectedPokemonInfo = expectedPokemonInfo

        val detailsResponse = Response.success(pokemonDetailsResponse)
        val speciesResponse = Response.success(pokemonSpeciesResponse)

        `when`(pokeApiService.getPokemonDetails("pokemonName")).thenReturn(detailsResponse)
        `when`(pokeApiService.getPokemonSpecies("pokemonName")).thenReturn(speciesResponse)

        val evolutionChainResponse = mockedEvolutionChainResponse
        val response = Response.success(evolutionChainResponse)
        `when`(pokeApiService.getEvolutionChain(any())).thenReturn(response)

        val actualPokemonInfo = pokeAPIRepository.fetchPokemonDetails("pokemonName")

        assertEquals(expectedPokemonInfo, actualPokemonInfo)
    }

}

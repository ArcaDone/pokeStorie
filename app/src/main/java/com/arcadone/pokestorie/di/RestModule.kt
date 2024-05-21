package com.arcadone.pokestorie.di

import android.app.Application
import android.content.Context
import com.arcadone.pokestorie.domain.usecase.GetPokemonListByUrlUseCase
import com.arcadone.pokestorie.domain.usecase.GetPokemonListByUrlUseCaseImpl
import com.arcadone.pokestorie.domain.usecase.GetPokemonListUseCase
import com.arcadone.pokestorie.domain.usecase.GetPokemonListUseCaseImpl
import com.arcadone.pokestorie.domain.usecase.GetSinglePokemonUseCase
import com.arcadone.pokestorie.domain.usecase.GetSinglePokemonUseCaseImpl
import com.arcadone.pokestorie.domain.repository.PokeAPIRepositoryInterface
import com.arcadone.pokestorie.domain.repository.PokeAPIRepositoryImpl
import com.arcadone.pokestorie.rest.PokeApiService
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.KotlinModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RestModule {

    @Provides
    @Singleton
    fun providesContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun providesObjectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(KotlinModule())
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    @Provides
    fun providesHttpClient(
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.MINUTES)
            .writeTimeout(15, TimeUnit.MINUTES)
            .readTimeout(15, TimeUnit.MINUTES)
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesPokeAPIService(
        objectMapper: ObjectMapper,
        okHttpClient: OkHttpClient
    ): PokeApiService {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
            .create(PokeApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesPokeAPIRepository(
        pokeApiService: PokeApiService,
    ): PokeAPIRepositoryInterface {
        return PokeAPIRepositoryImpl(
            pokeApiService = pokeApiService,
        )
    }

    @Provides
    @Singleton
    fun provideGetPokemonListUseCase(
        apiRepository: PokeAPIRepositoryInterface
    ): GetPokemonListUseCase =
        GetPokemonListUseCaseImpl(apiRepository)

    @Provides
    @Singleton
    fun provideGetPokemonListByUrlUseCase(
        apiRepository: PokeAPIRepositoryInterface
    ): GetPokemonListByUrlUseCase =
        GetPokemonListByUrlUseCaseImpl(apiRepository)

    @Provides
    @Singleton
    fun provideGetSinglePokemonUseCase(
        apiRepository: PokeAPIRepositoryInterface
    ): GetSinglePokemonUseCase =
        GetSinglePokemonUseCaseImpl(apiRepository)

}
package com.arcadone.pokestorie.di

import com.arcadone.pokestorie.networkconnection.NetworkConnection
import com.arcadone.pokestorie.networkconnection.NetworkConnectionImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkingModule {
    @Provides
    @Singleton
    fun provideNetworkConnection(netConn: NetworkConnectionImpl): NetworkConnection {
        return netConn
    }
}

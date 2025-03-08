package com.app.cinedimen.di

import com.app.cinedimen.data.network.detailscreen.MoviesDetailsNetwork
import com.app.cinedimen.data.network.listscreen.MainScreenNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideMainScreenNetwork(retrofit: Retrofit): MainScreenNetwork {
        return retrofit.create(MainScreenNetwork::class.java)
    }
    @Provides
    @Singleton
    fun provideMovieDetails(retrofit: Retrofit) : MoviesDetailsNetwork{
        return retrofit.create(MoviesDetailsNetwork::class.java)
    }
}

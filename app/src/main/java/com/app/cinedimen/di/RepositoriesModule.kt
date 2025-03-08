package com.app.cinedimen.di

import com.app.cinedimen.data.network.detailscreen.MoviesDetailsNetwork
import com.app.cinedimen.data.network.listscreen.MainScreenNetwork
import com.app.cinedimen.data.repositoriesImpl.detailscreen.RepositoriesImplMovieDetails
import com.app.cinedimen.data.repositoriesImpl.listscreen.RepositoriesImplMainScreen
import com.app.cinedimen.domain.repositories.detailscreen.RepositoriesMovieDetails
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesMainScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoriesMainScreen(mainScreenNetwork: MainScreenNetwork): RepositoriesMainScreen {
        return RepositoriesImplMainScreen(mainScreenNetwork)
    }


    @Provides
    @Singleton
    fun provideRepositoriesMovieDetails(moviesDetailsGET: MoviesDetailsNetwork): RepositoriesMovieDetails {
        return RepositoriesImplMovieDetails(moviesDetailsGET)
    }
}

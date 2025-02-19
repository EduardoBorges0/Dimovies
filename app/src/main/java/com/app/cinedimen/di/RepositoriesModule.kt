package com.app.cinedimen.di

import com.app.cinedimen.data.network.detailscreen.MovieReviewGET
import com.app.cinedimen.data.network.detailscreen.MoviesDetailsGET
import com.app.cinedimen.data.network.detailscreen.SimilarMoviesGET
import com.app.cinedimen.data.network.listscreen.NowPlayingGET
import com.app.cinedimen.data.network.listscreen.PopularGET
import com.app.cinedimen.data.network.listscreen.TopRatedGET
import com.app.cinedimen.data.network.listscreen.UpComingGET
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesMovieDetails
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesMovieReview
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesSimilarMovies
import com.app.cinedimen.data.repositories.listscreen.RepositoriesNowPlaying
import com.app.cinedimen.data.repositories.listscreen.RepositoriesPopularMovies
import com.app.cinedimen.data.repositories.listscreen.RepositoriesTopRated
import com.app.cinedimen.data.repositories.listscreen.RepositoriesUpComing
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
    fun provideRepositoriesNowPlaying(nowPlayingGET: NowPlayingGET): RepositoriesNowPlaying {
        return RepositoriesNowPlaying(nowPlayingGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesTopRated(topRatedGET: TopRatedGET): RepositoriesTopRated {
        return RepositoriesTopRated(topRatedGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesUpComing(upComingGET: UpComingGET): RepositoriesUpComing {
        return RepositoriesUpComing(upComingGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesPopularMovies(popularGET: PopularGET): RepositoriesPopularMovies {
        return RepositoriesPopularMovies(popularGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesMovieDetails(moviesDetailsGET: MoviesDetailsGET): RepositoriesMovieDetails {
        return RepositoriesMovieDetails(moviesDetailsGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesSimilarMovies(similarMoviesGET: SimilarMoviesGET): RepositoriesSimilarMovies {
        return RepositoriesSimilarMovies(similarMoviesGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesMovieReview(movieReviewGET: MovieReviewGET): RepositoriesMovieReview {
        return RepositoriesMovieReview(movieReviewGET)
    }
}

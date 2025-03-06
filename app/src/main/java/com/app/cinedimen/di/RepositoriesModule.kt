package com.app.cinedimen.di

import com.app.cinedimen.data.network.detailscreen.MovieReviewGET
import com.app.cinedimen.data.network.detailscreen.MoviesDetailsGET
import com.app.cinedimen.data.network.detailscreen.SimilarMoviesGET
import com.app.cinedimen.data.network.listscreen.NowPlayingGET
import com.app.cinedimen.data.network.listscreen.PopularGET
import com.app.cinedimen.data.network.listscreen.TopRatedGET
import com.app.cinedimen.data.network.listscreen.UpComingGET
import com.app.cinedimen.data.repositoriesImpl.detailscreen.RepositoriesImplMovieDetails
import com.app.cinedimen.data.repositoriesImpl.detailscreen.RepositoriesImplMovieReview
import com.app.cinedimen.data.repositoriesImpl.detailscreen.RepositoriesImplSimilarMovies
import com.app.cinedimen.data.repositoriesImpl.listscreen.RepositoriesImplNowPlaying
import com.app.cinedimen.data.repositoriesImpl.listscreen.RepositoriesImplPopularMovies
import com.app.cinedimen.data.repositoriesImpl.listscreen.RepositoriesImplTopRated
import com.app.cinedimen.data.repositoriesImpl.listscreen.RepositoriesImplUpComing
import com.app.cinedimen.domain.repositories.detailscreen.RepositoriesMovieDetails
import com.app.cinedimen.domain.repositories.detailscreen.RepositoriesMovieReview
import com.app.cinedimen.domain.repositories.detailscreen.RepositoriesSimilarMovies
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesNowPlaying
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesPopularMovies
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesTopRated
import com.app.cinedimen.domain.repositories.listscreen.RepositoriesUpComing
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
        return RepositoriesImplNowPlaying(nowPlayingGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesTopRated(topRatedGET: TopRatedGET): RepositoriesTopRated {
        return RepositoriesImplTopRated(topRatedGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesUpComing(upComingGET: UpComingGET): RepositoriesUpComing {
        return RepositoriesImplUpComing(upComingGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesPopularMovies(popularGET: PopularGET): RepositoriesPopularMovies {
        return RepositoriesImplPopularMovies(popularGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesMovieDetails(moviesDetailsGET: MoviesDetailsGET): RepositoriesMovieDetails {
        return RepositoriesImplMovieDetails(moviesDetailsGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesSimilarMovies(similarMoviesGET: SimilarMoviesGET): RepositoriesSimilarMovies {
        return RepositoriesImplSimilarMovies(similarMoviesGET)
    }

    @Provides
    @Singleton
    fun provideRepositoriesMovieReview(movieReviewGET: MovieReviewGET): RepositoriesMovieReview {
        return RepositoriesImplMovieReview(movieReviewGET)
    }
}

package com.app.cinedimen.data.repositoriesImpl.detailscreen

import com.app.cinedimen.data.model.MovieReviewsResponse
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.model.NowPlayingModel
import com.app.cinedimen.data.network.detailscreen.MoviesDetailsNetwork
import com.app.cinedimen.domain.repositories.detailscreen.RepositoriesMovieDetails
import retrofit2.Response

class RepositoriesImplMovieDetails(private val moviesDetails: MoviesDetailsNetwork) : RepositoriesMovieDetails {
    override suspend fun getMovieDetails(id: Int) : Response<MoviesDetails>{
        return moviesDetails.getMovieDetails(id = id)
    }
    override suspend fun getMovieReviews(movieId: Int) : Response<MovieReviewsResponse>{
        return moviesDetails.getReviews(movieId = movieId)
    }

    override suspend fun similarMovies(movieId: Int) : Response<NowPlayingModel>{
        return moviesDetails.getSimilarMovies(movieId = movieId)
    }
}
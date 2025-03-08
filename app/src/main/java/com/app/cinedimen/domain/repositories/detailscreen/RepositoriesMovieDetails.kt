package com.app.cinedimen.domain.repositories.detailscreen

import com.app.cinedimen.data.model.MovieReviewsResponse
import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.model.NowPlayingModel
import retrofit2.Response

interface RepositoriesMovieDetails {
    suspend fun getMovieDetails(id: Int) : Response<MoviesDetails>

    suspend fun getMovieReviews(movieId: Int) : Response<MovieReviewsResponse>

    suspend fun similarMovies(movieId: Int) : Response<NowPlayingModel>
}
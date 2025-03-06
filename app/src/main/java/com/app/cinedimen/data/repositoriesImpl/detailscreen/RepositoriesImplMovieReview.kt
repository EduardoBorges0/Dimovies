package com.app.cinedimen.data.repositoriesImpl.detailscreen

import com.app.cinedimen.data.model.MovieReviewsResponse
import com.app.cinedimen.data.network.detailscreen.MovieReviewGET
import com.app.cinedimen.domain.repositories.detailscreen.RepositoriesMovieReview
import retrofit2.Response

class RepositoriesImplMovieReview (private val movieReviewGET: MovieReviewGET) : RepositoriesMovieReview {
    override suspend fun getMovieReviews(movieId: Int) : Response<MovieReviewsResponse>{
        return movieReviewGET.getReviews(movieId = movieId)
    }
}
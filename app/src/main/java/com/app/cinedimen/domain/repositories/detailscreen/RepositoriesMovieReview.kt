package com.app.cinedimen.domain.repositories.detailscreen

import com.app.cinedimen.data.model.MovieReviewsResponse
import retrofit2.Response

interface RepositoriesMovieReview {
    suspend fun getMovieReviews(movieId: Int) : Response<MovieReviewsResponse>
}
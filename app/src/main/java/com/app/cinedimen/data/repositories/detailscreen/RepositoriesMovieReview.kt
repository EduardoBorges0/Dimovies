package com.app.cinedimen.data.repositories.detailscreen

import com.app.cinedimen.data.model.MovieReviewModel
import com.app.cinedimen.data.model.MovieReviewsResponse
import com.app.cinedimen.data.network.detailscreen.MovieReviewGET
import retrofit2.Response

class RepositoriesMovieReview (private val movieReviewGET: MovieReviewGET) {
    suspend fun getMovieReviews(movieId: Int) : Response<MovieReviewsResponse>{
        return movieReviewGET.getReviews(movieId = movieId)
    }
}
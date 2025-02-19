package com.app.cinedimen.data.repositories.detailscreen

import com.app.cinedimen.data.model.MovieReviewModel
import com.app.cinedimen.data.network.detailscreen.MovieReviewGET
import retrofit2.Response

class RepositoriesMovieReview (private val movieReviewGET: MovieReviewGET) {
    suspend fun getMovieReviews(movieId: Int) : Response<MovieReviewModel>{
        return movieReviewGET.getReviews(movieId = movieId)
    }
}
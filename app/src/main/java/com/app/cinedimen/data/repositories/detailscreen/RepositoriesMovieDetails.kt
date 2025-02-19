package com.app.cinedimen.data.repositories.detailscreen

import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.network.detailscreen.MoviesDetailsGET
import retrofit2.Response

class RepositoriesMovieDetails(private val moviesDetails: MoviesDetailsGET) {
    suspend fun getMovieDetails(id: Int) : Response<MoviesDetails>{
        return moviesDetails.getMovieDetails(id = id)
    }
}
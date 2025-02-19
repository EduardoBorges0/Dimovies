package com.app.cinedimen.domain.usecases.detailscreen

import com.app.cinedimen.data.model.MoviesDetails
import com.app.cinedimen.data.repositories.detailscreen.RepositoriesMovieDetails
import retrofit2.Response
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val repositoriesMovieDetails: RepositoriesMovieDetails) {


    suspend fun getMovieDetails(id: Int) : Response<MoviesDetails>{
     return repositoriesMovieDetails.getMovieDetails(id = id)
 }
}
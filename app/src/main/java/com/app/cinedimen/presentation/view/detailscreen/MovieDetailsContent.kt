package com.app.cinedimen.presentation.view.detailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.cinedimen.R
import com.app.cinedimen.presentation.view.components.AlertDialogError
import com.app.cinedimen.presentation.view.components.ListScreen
import com.app.cinedimen.presentation.view.detailscreen.MovieHeader.TopDetails
import com.app.cinedimen.presentation.view.detailscreen.Review.Reviews
import com.app.cinedimen.presentation.view.detailscreen.Sinopse.Sinopse
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieDetailsViewModel
import com.app.cinedimen.presentation.view.ui.theme.BackgroundColor

@Composable
fun MovieDetailsContent(navController: NavController,
                        movieDetailsViewModel: MovieDetailsViewModel,
                        movieId: Int
) {
    var showDialog by remember { mutableStateOf(true) }
    val movieDetails = movieDetailsViewModel.movieDetails.observeAsState().value
    val movieReview = movieDetailsViewModel.movieReview.observeAsState().value?.results
    val similarMovies = movieDetailsViewModel.similarMovie.observeAsState().value?.results ?: emptyList()
    val errorMessageDetailsMovie = movieDetailsViewModel.errorMessage.observeAsState().value
    val errorMessageMovieReview = movieDetailsViewModel.errorMessage.observeAsState().value
    val errorMessageSimilarMovies = movieDetailsViewModel.errorMessage.observeAsState().value


    val backdropPath = movieDetails?.backdrop_path ?: ""
    val posterPath = movieDetails?.poster_path ?: ""
    val title = movieDetails?.title ?: ""
    val runtime = movieDetails?.runtime ?: 0
    val genre = movieDetails?.genres ?: emptyList()
    val voteAverage = movieDetails?.vote_average ?: 0.0
    val sinopse = movieDetails?.overview ?:  stringResource(R.string.no_sinopse)

    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .background(BackgroundColor)){
          TopDetails(
              navController = navController,
              posterPath = posterPath,
              backdropPath = backdropPath,
              title = title,
              runtime = runtime,
              genres = genre,
              voteAverage = voteAverage
              )
          Sinopse(sinopse = sinopse)
        if (movieReview != null) {
                Reviews(reviews = movieReview)
        }
        ListScreen(navController = navController,
            movieSituation =  stringResource(R.string.similar),
            movieList = similarMovies,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .padding(horizontal = 20.dp)
        )
        if (!errorMessageDetailsMovie.isNullOrEmpty() ||
            !errorMessageMovieReview.isNullOrEmpty() ||
            !errorMessageSimilarMovies.isNullOrEmpty()) {
            showDialog = true

            val errorMessage = listOfNotNull(
                errorMessageDetailsMovie,
                errorMessageMovieReview,
                errorMessageSimilarMovies).joinToString("\n")
            if(showDialog){
                if(showDialog){
                    AlertDialogError(
                        confirmAction = {
                            movieDetailsViewModel.getMovieDetails(movieId)
                            movieDetailsViewModel.getMovieReview(movieId)
                            movieDetailsViewModel.getSimilarMovies(movieId)
                            showDialog = false
                        },
                        mainMessage = errorMessage
                    )
                }
            }
        }
    }
}
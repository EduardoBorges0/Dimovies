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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.cinedimen.presentation.view.components.AlertDialogError
import com.app.cinedimen.presentation.view.components.ListScreen
import com.app.cinedimen.presentation.view.detailscreen.MovieHeader.TopDetails
import com.app.cinedimen.presentation.view.detailscreen.Review.Reviews
import com.app.cinedimen.presentation.view.detailscreen.Sinopse.Sinopse
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieDetailsViewModel
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieReviewViewModel
import com.app.cinedimen.presentation.viewmodel.detailscreen.SimilarMoviesViewModel
import com.app.cinedimen.presentation.view.ui.theme.BackgroundColor

@Composable
fun MovieDetailsContent(navController: NavController,
                        movieDetailsViewModel: MovieDetailsViewModel,
                        movieReviewViewModel: MovieReviewViewModel,
                        movieSimilarMoviesViewModel: SimilarMoviesViewModel,
                        movieId: Int
) {
    var showDialog by remember { mutableStateOf(true) }
    val movieDetails = movieDetailsViewModel.movieDetails.observeAsState().value
    val movieReview = movieReviewViewModel.movieReview.observeAsState().value?.results
    val similarMovies = movieSimilarMoviesViewModel.similarMovie.observeAsState().value?.results ?: emptyList()
    val errorMessageDetailsMovie = movieDetailsViewModel.errorMessage.observeAsState().value
    val errorMessageMovieReview = movieReviewViewModel.errorMessage.observeAsState().value
    val errorMessageSimilarMovies = movieSimilarMoviesViewModel.errorMessage.observeAsState().value


    val backdropPath = movieDetails?.backdrop_path ?: ""
    val posterPath = movieDetails?.poster_path ?: ""
    val title = movieDetails?.title ?: ""
    val runtime = movieDetails?.runtime ?: 0
    val genre = movieDetails?.genres ?: emptyList()
    val voteAverage = movieDetails?.vote_average ?: 0.0
    val sinopse = movieDetails?.overview ?: ""

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
            movieSituation = "Mais como Este",
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
                            movieReviewViewModel.getMovieReview(movieId)
                            movieSimilarMoviesViewModel.getSimilarMovies(movieId)
                            showDialog = false
                        },
                        mainMessage = errorMessage
                    )
                }
            }
        }
    }
}
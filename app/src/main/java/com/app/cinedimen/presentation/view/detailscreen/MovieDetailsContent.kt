package com.app.cinedimen.presentation.view.detailscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.app.cinedimen.presentation.view.detailscreen.MovieHeader.TopDetails
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieDetailsViewModel
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieReviewViewModel
import com.app.cinedimen.ui.theme.BackgroundColor

@Composable
fun MovieDetailsContent(navController: NavController,
                        movieDetailsViewModel: MovieDetailsViewModel,
                        movieReviewViewModel: MovieReviewViewModel
) {
    val movieDetails = movieDetailsViewModel.movieDetails.observeAsState().value
    val movieReview = movieReviewViewModel.movieReview.observeAsState().value?.results
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
    }
}
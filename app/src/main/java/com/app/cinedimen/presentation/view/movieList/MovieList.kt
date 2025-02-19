package com.app.cinedimen.presentation.view.movieList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.app.cinedimen.presentation.viewmodel.listscreen.NowPlayingViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.PopularMovieViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.TopRatedMoviesViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.UpComingViewModel
import com.app.cinedimen.ui.theme.BackgroundColor
import com.app.cinedimen.utils.ImageUtils

@Composable
fun MovieList(
    nowPlayingViewModel: NowPlayingViewModel,
    popularMovieViewModel: PopularMovieViewModel,
    topRatedMoviesViewModel: TopRatedMoviesViewModel,
    upComingViewModel: UpComingViewModel
) {
    val nowPlayingMovies = nowPlayingViewModel.nowPlayingMovies.observeAsState()

   Box(modifier = Modifier
       .fillMaxSize()
       ){
       AsyncImage(
           model = "https://image.tmdb.org/t/p/original/pzIddUEMWhWzfvLI3TwxUG2wGoi.jpg",
           contentDescription = "Filme"
       )
   }
}
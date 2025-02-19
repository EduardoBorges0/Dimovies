package com.app.cinedimen.presentation.view.movieList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.cinedimen.R
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
    val isLoading = nowPlayingViewModel.isLoading.observeAsState()
    val errorMessage = nowPlayingViewModel.errorMessage.observeAsState()
    val widthSize = LocalConfiguration.current.screenWidthDp
    val heigthSize = LocalConfiguration.current.screenHeightDp
    Box(modifier = Modifier
       .fillMaxSize()
        .background(BackgroundColor)
       ){
        AsyncImage(model = R.drawable.movieyou, contentDescription = "logo", modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = heigthSize.dp / 15)
            .size(70.dp))
        if(isLoading.value == true){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.White)
        }
       LazyRow {
           items(nowPlayingMovies.value?.results ?: emptyList()) { movie ->
                   AsyncImage(
                       model = "https://image.tmdb.org/t/p/original${movie.poster_path}",
                       contentDescription = "Filme",
                       modifier = Modifier
                           .clip(RoundedCornerShape(8.dp))
                           .padding(top = 160.dp)
                           .size(heigthSize.dp/4)
                   )
           }
       }
   }
}
package com.app.cinedimen.presentation.view.movieList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.app.cinedimen.R
import com.app.cinedimen.data.model.Result
import com.app.cinedimen.presentation.viewmodel.listscreen.NowPlayingViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.PopularMovieViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.TopRatedMoviesViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.UpComingViewModel
import com.app.cinedimen.ui.theme.BackgroundColor

@Composable
fun MainScreen(
    navController: NavController,
    nowPlayingViewModel: NowPlayingViewModel,
    popularMovieViewModel: PopularMovieViewModel,
    topRatedMoviesViewModel: TopRatedMoviesViewModel,
    upComingViewModel: UpComingViewModel
) {
    val nowPlayingMovies = nowPlayingViewModel.nowPlayingMovies.observeAsState().value?.results ?: emptyList()
    val upComingMovies = upComingViewModel.upComingMovies.observeAsState().value?.results ?: emptyList()
    val popularMovies = popularMovieViewModel.popularMovies.observeAsState().value?.results ?: emptyList()
    val topRatedMovies = topRatedMoviesViewModel.topRated.observeAsState().value?.results ?: emptyList()
    val isLoading = nowPlayingViewModel.isLoading.observeAsState().value ?: false

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp) // Espaço entre os itens
    ) {
        AsyncImage(
            model = R.drawable.movieyou,
            contentDescription = "logo",
            modifier = Modifier
                .padding(top = screenHeight / 15)
                .size(70.dp)
        )

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            MovieList(navController = navController, movieSituation = "Em Exibição", movieList = nowPlayingMovies)
            MovieList(navController = navController, movieSituation = "Em breve", movieList = upComingMovies)
            MovieList(navController = navController, movieSituation = "Mais Populares", movieList = popularMovies)
            MovieList(navController = navController, movieSituation = "Mais Bem Avaliados", movieList = topRatedMovies)
        }
    }
}

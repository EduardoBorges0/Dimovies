package com.app.cinedimen.presentation.view.navigations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import androidx.activity.viewModels

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.cinedimen.presentation.view.movieList.MovieList
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieDetailsViewModel
import com.app.cinedimen.presentation.viewmodel.detailscreen.SimilarMoviesViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.NowPlayingViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.PopularMovieViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.TopRatedMoviesViewModel
import com.app.cinedimen.presentation.viewmodel.listscreen.UpComingViewModel
import com.app.cinedimen.ui.theme.CineDimenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationsActivity : ComponentActivity() {
    private val nowPlayingViewModel : NowPlayingViewModel by viewModels()
    private val topRatedMoviesViewModel : TopRatedMoviesViewModel by viewModels()
    private val upComingViewModel : UpComingViewModel by viewModels()
    private val popularMovieViewModel: PopularMovieViewModel by viewModels()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val similarMoviesViewModel: SimilarMoviesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LaunchedEffect(Unit) {
                similarMoviesViewModel.getSimilarMovies(8)
            }
            CineDimenTheme {
                    NavigationsRoutes(
                        nowPlayingViewModel = nowPlayingViewModel,
                        popularMovieViewModel = popularMovieViewModel,
                        topRatedMoviesViewModel = topRatedMoviesViewModel,
                        upComingViewModel = upComingViewModel
                    )
            }
        }
    }
}
@Composable
fun NavigationsRoutes(
    nowPlayingViewModel: NowPlayingViewModel,
    popularMovieViewModel: PopularMovieViewModel,
    topRatedMoviesViewModel: TopRatedMoviesViewModel,
    upComingViewModel: UpComingViewModel
) {
   val navController = rememberNavController()
   NavHost(navController = navController, startDestination = "movieList"){
       composable("movieList") {
           MovieList(
               nowPlayingViewModel = nowPlayingViewModel,
               popularMovieViewModel = popularMovieViewModel,
               topRatedMoviesViewModel = topRatedMoviesViewModel,
               upComingViewModel = upComingViewModel
           )
       }
   }
}

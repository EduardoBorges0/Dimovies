package com.app.cinedimen.presentation.navigations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.cinedimen.presentation.view.detailscreen.MovieDetailsContent
import com.app.cinedimen.presentation.view.mainScreen.MainScreen
import com.app.cinedimen.presentation.view.splashScreen.SplashScreen
import com.app.cinedimen.presentation.viewmodel.detailscreen.MovieDetailsViewModel
import com.app.cinedimen.presentation.view.ui.theme.CineDimenTheme
import com.app.cinedimen.presentation.viewmodel.listscreen.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineDimenTheme {
                NavigationsRoutes()
            }
        }
    }
}

@Composable
fun NavigationsRoutes(
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("movieList") {
            val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                mainScreenViewModel.getMoviesNowPlaying()
                mainScreenViewModel.getUpComingMovies()
                mainScreenViewModel.getPopularMovies()
                mainScreenViewModel.getTopRated()
            }
            MainScreen(
                navController = navController,
                mainScreenViewModel = mainScreenViewModel
            )
        }
        composable("movieDetails/{movieId}") { backStackEntry ->
            val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()

            val movieId =
                backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: return@composable

            LaunchedEffect(movieId) {
                movieDetailsViewModel.getMovieDetails(movieId)
                movieDetailsViewModel.getMovieReview(movieId)
                movieDetailsViewModel.getSimilarMovies(movieId)
            }
            MovieDetailsContent(
                movieDetailsViewModel = movieDetailsViewModel,
                navController = navController,
                movieId = movieId
            )
        }
    }
}

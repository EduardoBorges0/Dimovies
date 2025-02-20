package com.app.cinedimen.presentation.view.detailscreen.MovieHeader

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.app.cinedimen.data.model.Genre
import com.app.cinedimen.utils.ImageUtils

@Composable
fun TopDetails(navController: NavController,
               posterPath: String,
               backdropPath: String,
               title: String,
               runtime: Int,
               genres: List<Genre>,
               voteAverage: Double
               ) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(440.dp)) {

        ArrowBack(
            navController = navController,
            modifier = Modifier
                .align(Alignment.TopStart)
        )

        AsyncImage(
            model = ImageUtils.getImageUrl(path = backdropPath),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                .graphicsLayer { alpha = 0.3f }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                        startY = 300f
                    )
                )
        )
        AsyncImage(
            model = ImageUtils.getImageUrl(path = posterPath),
            contentDescription = "Movie Poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(26.dp)
                .size(height = 230.dp, width = 160.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        MovieOverview(movieName = title,
            movieTime = runtime,
            movieGenres = genres,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 120.dp),
            movieRated = voteAverage
        )
}
}
@Composable
fun ArrowBack(navController: NavController, modifier: Modifier){
    Icon(
        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
        contentDescription = "arrow back",
        modifier = modifier
            .padding(36.dp, top = 50.dp)
            .size(35.dp)
            .clickable {
            navController.popBackStack()
        },
        tint = Color.White,
    )
}
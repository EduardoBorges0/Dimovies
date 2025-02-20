package com.app.cinedimen.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.app.cinedimen.data.model.Result
import com.app.cinedimen.utils.ImageUtils

@Composable
fun ListScreen(navController: NavController,
              movieSituation: String,
              movieList: List<Result>,
              modifier: Modifier) {
    val heigthSize = LocalConfiguration.current.screenHeightDp
    Column(modifier = modifier) {
        Text(
            text = movieSituation,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 10.dp)
                .padding(horizontal = 10.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
        ) {
            items(movieList) { movie ->
                AsyncImage(
                    model = ImageUtils.getImageUrl(movie.poster_path),
                    contentDescription = "Filme",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .height(heigthSize.dp / 3.95f)
                        .clickable {
                          navController.navigate("movieDetails/${movie.id}")
                        }
                )
            }
        }
    }
}

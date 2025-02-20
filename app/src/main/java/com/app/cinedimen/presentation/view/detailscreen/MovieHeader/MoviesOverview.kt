package com.app.cinedimen.presentation.view.detailscreen.MovieHeader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.cinedimen.R
import com.app.cinedimen.data.model.Genre

@Composable
fun MovieOverview(movieName: String,
                  movieTime: Int,
                  movieGenres: List<Genre>,
                  movieRated: Double,
                  modifier: Modifier
){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val genreText = movieGenres.take(2).joinToString(" • ") { it.name }

    val horas = movieTime / 60
    val minutos = movieTime % 60
    val runtime = if (movieTime >= 60) "$horas hora(s) $minutos minuto(s)" else "$movieTime minuto(s)"

    Column (modifier = modifier
        .padding(horizontal = 30.dp)){
        Text(movieName,
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .width(screenWidth / 3.1f))

        Text(text = runtime,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .width(screenWidth/ 2.3f)
                .padding(top = 20.dp))

        Row (modifier = Modifier
            .width(200.dp)
            .padding(top = 5.dp)
            ){
            when (movieGenres.size) {
                0 -> {}
                1 -> Text(
                    movieGenres[0].name,
                    color = Color.White)
                else -> Text(text = genreText, color = Color.White)

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "rating",
                tint = Color.Yellow,
                modifier = Modifier.size(20.dp)
            )

            Text(
                String.format("%.1f / 10", movieRated),
                color = Color.Gray,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
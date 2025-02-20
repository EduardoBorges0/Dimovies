package com.app.cinedimen.presentation.view.detailscreen.MovieHeader

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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

    val horas = movieTime / 60
    val minutos = movieTime % 60
    Column (modifier = modifier.padding(horizontal = 30.dp)){
        Text(movieName,
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.opensansregular)),

            modifier = Modifier.width(screenWidth / 3.1f))

        Text(text = if(movieTime >= 60) "${horas} hora(s) ${minutos} minuto(s)" else "${movieTime} minuto(s)",
            color = Color.White,
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.opensansregular)),

            modifier = Modifier.width(screenWidth/ 2.3f).padding(top = 20.dp))

        Row (modifier = Modifier.fillMaxWidth()){
            if(movieGenres.size > 0){
                Text(if(movieGenres.size == 1) movieGenres[0].name else "${movieGenres[0].name} • ${movieGenres[1].name}",
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.opensansregular)),
                )
            }
        }
        Row (modifier = Modifier.fillMaxWidth()){
            AsyncImage(model = R.drawable.star,
                contentDescription = "rating",
                modifier = Modifier.padding(top = 12.dp).size(20.dp))
            Text(String.format("%.1f / 10 Média de votos", movieRated),
                fontFamily = FontFamily(Font(R.font.opensansregular)),
                color = Color.Gray,
                modifier = Modifier.padding(top = 10.dp).padding(horizontal = 8.dp).width(screenWidth/ 2.8f))
        }
    }
}
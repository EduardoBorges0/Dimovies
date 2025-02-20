package com.app.cinedimen.presentation.view.detailscreen.Review

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.cinedimen.data.model.MovieReviewModel
import com.app.cinedimen.presentation.view.ui.theme.ReviewBoxColor

@Composable
fun Reviews(reviews: List<MovieReviewModel>) {
    Text("Comentários",
        color = Color.White,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 32.dp)
    )
    Box(modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier
            .padding(horizontal = 35.dp)
            .padding(top = 15.dp, bottom = 25.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(ReviewBoxColor)
            .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
            .width(350.dp)
            .wrapContentHeight()
        ){
            if(reviews.size > 0){
                reviews.forEach { review ->
                    Text(review.author,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 15.dp),
                        color = Color.White
                    )
                    Text(review.content,
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .padding(top = 5.dp),
                        color = Color.Gray,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    HorizontalDivider(
                        color = Color.Gray,
                        modifier = Modifier
                        .padding(bottom = 20.dp, top = 15.dp)
                        .padding(horizontal = 10.dp)
                    )
                }

            }else{
                Text("Sem comentários",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White)
            }
        }
    }
}
package com.app.cinedimen.presentation.view.detailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.cinedimen.R
import com.app.cinedimen.data.model.MovieReviewModel
import com.app.cinedimen.ui.theme.ReviewBoxColor

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
            .padding(top = 15.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ReviewBoxColor)
            .width(350.dp)
            .wrapContentHeight()
        ){
            if(reviews.size > 0){
                for(i in 0 until reviews.size){
                    Text(reviews[i].author,
                        modifier = Modifier.padding(16.dp),
                        color = Color.White)
                    Text(reviews[i].content,
                        modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 10.dp),
                        color = Color.Gray,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis)
                    HorizontalDivider(color = Color.Gray, modifier = Modifier.padding(bottom = 20.dp).padding(horizontal = 10.dp))
                }
            }else{
                Text("Sem comentários",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White)
            }

        }
    }

}
package com.app.cinedimen.presentation.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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
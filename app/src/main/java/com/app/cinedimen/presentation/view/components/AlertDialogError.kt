package com.app.cinedimen.presentation.view.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AlertDialogError(
    mainMessage: String,
    confirmAction : () -> Unit
) {

    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = "Erro") },
        text = { Text(mainMessage, color = Color.White) },
        confirmButton = {
            TextButton(
                onClick = {
                    confirmAction()
                }
            ) {
                Text(text = "Tentar Novamente", color = Color.White)
            }
        }
    )
}
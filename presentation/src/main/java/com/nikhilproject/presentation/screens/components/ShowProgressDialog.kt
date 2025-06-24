package com.nikhilproject.presentation.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ShowProgressDialog(showLoading: Boolean) {
    if (showLoading) {
        Dialog(onDismissRequest = {}) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.Cyan,
                    strokeWidth = 5.dp,
                    strokeCap = StrokeCap.Butt
                )
            }
        }
    }
}
package com.nikhilproject.presentation.screens.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToastMessage(messageToShow: String?, message: (String?) -> Unit) {
    val context = LocalContext.current

    messageToShow?.let { message ->
        LaunchedEffect(message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            message(null)
        }
    }
}
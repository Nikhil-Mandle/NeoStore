package com.nikhilproject.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigationIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    onEndIconClick: () -> Unit,
    onNavigationIconClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.background(Color.Green),
        title = { Text(text = title, textAlign = TextAlign.Center) },
        actions = {
            IconButton(onClick = onEndIconClick) {
                endIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Add"
                    )
                }
            }
        },
        navigationIcon = {
                IconButton(onClick = {
                    onNavigationIconClick()
                }) {
                    Icon(navigationIcon ?: Icons.Default.ArrowBack, contentDescription = "Back")
                }

        }
    )
}
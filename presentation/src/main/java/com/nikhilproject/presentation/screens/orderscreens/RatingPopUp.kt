package com.nikhilproject.presentation.screens.orderscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nikhilproject.presentation.R

@Composable
fun ProductRatingDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (Int) -> Unit
) {
    if (showDialog) {
        var rating by remember { mutableIntStateOf(0) }

        Dialog(onDismissRequest = onDismiss) {
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Rate this product",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Optional product image
                    Image(
                        painter = painterResource(id = R.drawable.table),
                        contentDescription = "Product Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(bottom = 16.dp)
                    )

                    // Rating Bar
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        for (i in 1..5) {
                            Icon(
                                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "Star $i",
                                tint = if (i <= rating) Color(0xFFFFC107) else Color.LightGray,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { rating = i }
                            )
                        }
                    }

                    Button(
                        onClick = {
                            onSubmit(rating)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "RATE NOW", color = Color.White)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun RatingPopUpPreview() {
    ProductRatingDialog(showDialog = true, {}, {})
}
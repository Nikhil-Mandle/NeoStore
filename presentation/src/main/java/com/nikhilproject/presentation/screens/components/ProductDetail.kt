package com.nikhilproject.presentation.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nikhilproject.domain.model.ProductDetailsData
import com.nikhilproject.presentation.UiState


@Composable
fun ProductDetail(
    productState: UiState<ProductDetailsData>,
    showQuantityDialog: (Boolean) -> Unit,
    showRatingDialog: (Boolean) -> Unit
) {
    when (productState) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val product = (productState as UiState.Success<ProductDetailsData>).data

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(text = product.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Text(
                    text = "Category ID - ${product.product_category_id}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = product.producer,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .weight(1f)
                    ) {
                        repeat(5) { index ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = if (index < product.rating) Color(0xFFFFC107) else Color.LightGray
                            )
                        }
                    }
                }

                Text(
                    text = "Rs. ${product.cost}",
                    color = Color.Red,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )

                product.product_images.firstOrNull()?.let { mainImage ->
                    AsyncImage(
                        model = mainImage.image,
                        contentDescription = product.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(vertical = 8.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    product.product_images.forEach { img ->
                        AsyncImage(
                            model = img.image,
                            contentDescription = "Thumbnail",
                            modifier = Modifier
                                .size(80.dp)
                                .border(1.dp, Color.LightGray)
                        )
                    }
                }

                Text(
                    text = "DESCRIPTION",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                )

                Text(
                    text = product.description,
                    fontSize = 14.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth()
                            .weight(1f)
                            .padding(end = 8.dp),
                        onClick = {
                            showQuantityDialog(true)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("ADD TO CART", color = Color.White)
                    }

                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth()
                            .weight(1f)
                            .padding(start = 8.dp),
                        onClick = {
                            showRatingDialog(true)
                        },
                        border = BorderStroke(1.dp, Color.Gray)
                    ) {
                        Text("RATE")
                    }
                }
            }
        }

        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = (productState as UiState.Error).message,
                    color = Color.Red
                )
            }
        }

        UiState.Idle -> {}
    }
}

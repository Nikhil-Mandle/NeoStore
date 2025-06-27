package com.nikhilproject.presentation.screens.orderscreens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.nikhilproject.domain.model.FetchOrderDetailsData
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.viewmodel.OrderViewModel
import com.nikhilproject.presentation.viewmodel.UserViewModel

@Composable
fun OrderDetailsScreen(id: Int) {
    val orderViewModel: OrderViewModel = hiltViewModel()
    val authViewModel: UserViewModel = hiltViewModel()
    val token = authViewModel.getAccessToken()

    val state by orderViewModel.orderDetailState.collectAsState()

    LaunchedEffect(Unit) {
        orderViewModel.fetchOrderDetail(token ?: "", id)
    }

    when (state) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val data = (state as UiState.Success<FetchOrderDetailsData>).data
            val orderItems = data.order_details.map {
                OrderItem(
                    name = it.prod_name,
                    category = it.prod_cat_name,
                    qty = it.quantity,
                    amount = it.total.toDouble(),
                    imageResId = it.prod_image
                )
            }
            val totalAmount = data.cost

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                items(orderItems) { item ->
                    OrderDetailsItem(item)
                    HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "TOTAL",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "₹ %.2f".format(totalAmount),
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }

        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Error: ${(state as UiState.Error).message}",
                    color = Color.Red
                )
            }
        }

        UiState.Idle -> {}
    }
}


@Composable
fun OrderDetailsItem(item: OrderItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(item.imageResId),
            contentDescription = item.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(60.dp)
                .padding(end = 12.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "(${item.category})",
                style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "QTY : ${item.qty}",
                style = MaterialTheme.typography.bodySmall
            )
        }

        Text(
            text = "₹ %.2f".format(item.amount),
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}


data class OrderItem(
    val name: String,
    val category: String,
    val qty: Int,
    val amount: Double,
    val imageResId: String? = null
)


@Composable
fun SampleOrderDetailsScreen() {
    OrderDetailsScreen(1)
}

@Preview
@Composable
private fun SampleOrderDetailsScreenPreview() {
    SampleOrderDetailsScreen()
}
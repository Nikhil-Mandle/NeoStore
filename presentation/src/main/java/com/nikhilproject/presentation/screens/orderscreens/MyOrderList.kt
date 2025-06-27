package com.nikhilproject.presentation.screens.orderscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhilproject.domain.model.OrderDetails
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.viewmodel.OrderViewModel
import com.nikhilproject.presentation.viewmodel.UserViewModel

@Composable
fun MyOrdersList(onItemClick: (Int) -> Unit) {
    val orderViewModel: OrderViewModel = hiltViewModel()
    val authViewModel: UserViewModel = hiltViewModel()
    val token = authViewModel.getAccessToken()

    val uiState by orderViewModel.allOrdersState.collectAsState()

    LaunchedEffect(Unit) {
        orderViewModel.getAllOrders(accessToken = token ?: "")
    }

    when (uiState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val orders = (uiState as UiState.Success<List<OrderDetails>>).data.map {
                Order(
                    orderId = it.id,
                    orderDate = it.created,
                    amount = "₹%.2f".format(it.cost)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                items(orders) { order ->
                    OrderItem(order = order) {
                        onItemClick(order.orderId)
                    }
                    HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                }
            }
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error: ${(uiState as UiState.Error).message}",
                    color = Color.Red
                )
            }
        }

        UiState.Idle -> {

        }
    }
}

@Composable
fun OrderItem(order: Order, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onItemClick()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Order ID : ${order.orderId}",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = order.amount,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Ordered Date : ${order.orderDate}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

data class Order(
    val orderId: Int,
    val orderDate: String,
    val amount: String
)

@Preview
@Composable
private fun MyOrdersListPreview() {
    MyOrdersList() {}
}
package com.nikhilproject.presentation.screens.orderscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyOrdersList() {
    val orders = listOf(
        Order("15879", "07 Aug' 15", "₹45.00"),
        Order("17846", "08 Aug' 15", "₹90.00"),
        Order("17977", "08 Aug' 15", "₹45.00"),
        Order("15879", "07 Aug' 15", "₹45.00"),
        Order("17846", "08 Aug' 15", "₹90.00"),
        Order("17977", "08 Aug' 15", "₹45.00"),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(orders) { order ->
            OrderItem(order)
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
fun OrderItem(order: Order) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
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
    val orderId: String,
    val orderDate: String,
    val amount: String
)

@Preview
@Composable
private fun MyOrdersListPreview() {
    MyOrdersList()
}
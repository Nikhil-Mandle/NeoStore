package com.nikhilproject.presentation.screens.cartscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikhilproject.presentation.R

@Composable
fun MyCartScreen(onOrderNowClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val items = listOf(
            CartItem("Pembroke", "Table", "₹45.00", R.drawable.table),
            CartItem("Adirondack", "Chair", "₹90.00", R.drawable.chair),
            CartItem("Chesterfield", "Sofa", "₹45.00", R.drawable.empty_cart)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items) { item ->
                CartItemRow(item)
            }
        }

        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("TOTAL", fontWeight = FontWeight.Bold)
            Text("₹180.00", fontWeight = FontWeight.Bold)
        }

        Button(
            onClick = {
                onOrderNowClicked()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
        ) {
            Text("ORDER NOW", color = Color.White)
        }
    }
}

@Composable
fun CartItemRow(item: CartItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(item.name, fontWeight = FontWeight.Bold)
            Text("(${item.type})", style = MaterialTheme.typography.bodySmall)
            QuantitySelector()
        }

        Column(horizontalAlignment = Alignment.End) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red,
                    modifier = Modifier.size(30.dp)
                )
            }
            Text(item.price)
        }
    }
}

@Composable
fun QuantitySelector() {
    var quantity by remember { mutableStateOf(1) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { if (quantity > 1) quantity-- }) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Remove")
        }
        Text(quantity.toString())
        IconButton(onClick = { quantity++ }) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Add")
        }
    }
}

data class CartItem(
    val name: String,
    val type: String,
    val price: String,
    val imageRes: Int
)


@Preview
@Composable
fun MyCartScreenPreview(){
    MyCartScreen(){}
}
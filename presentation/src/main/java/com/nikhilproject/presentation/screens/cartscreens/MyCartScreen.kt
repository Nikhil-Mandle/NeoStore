package com.nikhilproject.presentation.screens.cartscreens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.nikhilproject.domain.model.CartListResponse
import com.nikhilproject.domain.model.ProductItemData
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.viewmodel.CartViewModel
import com.nikhilproject.presentation.viewmodel.UserViewModel

@Composable
fun MyCartScreen(onOrderNowClicked: () -> Unit) {

    val cartViewModel: CartViewModel = hiltViewModel()
    val userViewModel: UserViewModel = hiltViewModel()

    val accessToken = userViewModel.getAccessToken()
    val cartState by cartViewModel.cartListState.collectAsState()


    LaunchedEffect(Unit) {
        cartViewModel.fetchCartItems(accessToken ?: "")
    }

    when (cartState) {
        is UiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val response = (cartState as UiState.Success<CartListResponse>).data
            val cartItems = response.data

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(cartItems) { item ->
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
                    Text("₹${response.total}", fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = onOrderNowClicked,
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

        is UiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${(cartState as UiState.Error).message}", color = Color.Red)
            }
        }

        UiState.Idle -> {} // No-op
    }
}

@Composable
fun CartItemRow(item: ProductItemData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(item.product.product_images),
            contentDescription = item.product.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(item.product.name, fontWeight = FontWeight.Bold)
            Text("(${item.product.product_category})", style = MaterialTheme.typography.bodySmall)
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
            Text(text = item.product.sub_total.toString())
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

@Preview
@Composable
fun MyCartScreenPreview() {
    MyCartScreen() {}
}
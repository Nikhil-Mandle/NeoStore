package com.nikhilproject.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.nikhilproject.domain.model.ProductItem
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(
    categoryId: Int,
    viewModel: ProductViewModel = hiltViewModel(),
    onItemClick: (Int, String, String) -> Unit
) {
    LaunchedEffect(true) {
        viewModel.fetchProductList(categoryId)
    }

    val uiState by viewModel.productListState.collectAsStateWithLifecycle()

    when (uiState) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val itemList = (uiState as UiState.Success<List<ProductItem>>).data

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(itemList) { item ->
                    FurnitureListItem(item = item) { id, name, image ->
                        onItemClick(id, name, image)
                    }
                    HorizontalDivider(color = Color.LightGray)
                }
            }
        }

        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (uiState as UiState.Error).message, color = Color.Red)
            }
        }

        UiState.Idle -> {

        }
    }
}


@Composable
fun FurnitureListItem(item: ProductItem, onClick: (Int, String, String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(item.id, item.name, item.product_images) },
    ) {
        AsyncImage(
            model = item.product_images,
            contentDescription = item.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(4.dp))
                .border(1.dp, Color.Gray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = item.producer,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Text(
                text = "Rs. ${item.cost}",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            StarRating(rating = item.rating)
        }
    }
}


@Composable
fun StarRating(rating: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) { index ->
            val starColor = if (index < rating) Color(0xFFFFC107) else Color.LightGray
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = starColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

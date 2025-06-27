package com.nikhilproject.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikhilproject.domain.model.ProductCategory
import com.nikhilproject.presentation.R
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.screens.components.ImageSlider
import com.nikhilproject.presentation.viewmodel.HomeScreenViewModel
import com.nikhilproject.presentation.viewmodel.UserViewModel

@Composable
fun HomeScreen(
    onItemClick: (Int) -> Unit,
) {
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val userViewModel = hiltViewModel<UserViewModel>()
    val token = userViewModel.getAccessToken()

    val uiState by homeScreenViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        homeScreenViewModel.fetchCategories(accessToken = token ?: "")
    }

    when (uiState) {
        is UiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val categories = (uiState as UiState.Success<List<ProductCategory>>).data

            val imageList = remember(uiState) {
                if (uiState is UiState.Success) {
                    categories.filter { it.icon_image.isNotEmpty() }.map { it.icon_image }
                } else {
                    emptyList()
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ImageSlider(imageList)

                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(categories) { category ->

                        val (imageResId, name) = when (category.id) {
                            1 -> Pair(R.drawable.table, "Tables")
                            2 -> Pair(R.drawable.chair, "Chairs")
                            3 -> Pair(R.drawable.sofa, "Sofas")
                            4 -> Pair(R.drawable.closet, "Cupboards")
                            else -> {
                                Pair(R.drawable.empty_cart, "")
                            }
                        }

                        CategoryCard(
                            category = Category(
                                categoryId = category.id,
                                name = name,
                                image = imageResId,
                                bgColor = Color.Red
                            )
                        ){ id ->
                            onItemClick(id)
                        }
                    }
                }
            }
        }

        is UiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${(uiState as UiState.Error).message}", color = Color.White)
            }
        }

        UiState.Idle -> {}
    }
}

@Composable
fun CategoryCard(category: Category, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .clickable {
                onClick(category.categoryId)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(category.image),
                contentDescription = category.name,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = category.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
    }
}

data class Category(val categoryId: Int,val name: String, val image: Int, val bgColor: Color)

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen() {}
}
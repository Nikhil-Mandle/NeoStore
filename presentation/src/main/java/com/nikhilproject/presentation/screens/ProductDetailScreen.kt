package com.nikhilproject.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nikhilproject.domain.model.CartOperationResponse
import com.nikhilproject.presentation.UiState
import com.nikhilproject.presentation.screens.components.ProductDetail
import com.nikhilproject.presentation.screens.components.ShowProgressDialog
import com.nikhilproject.presentation.screens.components.ShowToastMessage
import com.nikhilproject.presentation.screens.orderscreens.ProductOrderDialog
import com.nikhilproject.presentation.screens.orderscreens.ProductRatingDialog
import com.nikhilproject.presentation.viewmodel.UserViewModel
import com.nikhilproject.presentation.viewmodel.CartViewModel
import com.nikhilproject.presentation.viewmodel.ProductViewModel

@Composable
fun ProductDetailScreen(
    productId: Int,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val userViewModel: UserViewModel = hiltViewModel()
    val cartViewModel: CartViewModel = hiltViewModel()

    val productState by viewModel.productDetailState.collectAsStateWithLifecycle()
    val ratingState by viewModel.setRatingState.collectAsStateWithLifecycle()
    val addToCartState by cartViewModel.addToCartState.collectAsStateWithLifecycle()


    var showRatingDialog by remember { mutableStateOf(false) }
    var showQuantityDialog by remember { mutableStateOf(false) }

    var showLoading by remember { mutableStateOf(false) }
    var messageToShow by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(productId) {
        viewModel.fetchProductDetails(productId)
    }


    LaunchedEffect(addToCartState) {
        when (addToCartState) {
            is UiState.Loading -> showLoading = true
            is UiState.Success -> {
                showLoading = false
                messageToShow =
                    (addToCartState as UiState.Success<CartOperationResponse>).data.message
                showRatingDialog = false
            }

            is UiState.Error -> {
                showLoading = false
                messageToShow = (addToCartState as UiState.Error).message
                showRatingDialog = false
            }

            UiState.Idle -> Unit
        }
    }



    LaunchedEffect(ratingState) {
        when (ratingState) {
            is UiState.Loading -> showLoading = true
            is UiState.Success -> {
                showLoading = false
                messageToShow = (ratingState as UiState.Success<String>).data
                showRatingDialog = false
            }

            is UiState.Error -> {
                showLoading = false
                messageToShow = (ratingState as UiState.Error).message
                showRatingDialog = false
            }

            UiState.Idle -> Unit
        }
    }

    ShowProgressDialog(showLoading)

    ShowToastMessage(messageToShow) {
        messageToShow = it
    }

    ProductDetail(productState, showQuantityDialog = {
        showQuantityDialog = it
    }) {
        showRatingDialog = it
    }

    ProductRatingDialog(
        showDialog = showRatingDialog,
        onDismiss = { showRatingDialog = false },
        onSubmit = { rating ->
            viewModel.setProductRating(productId, rating)
        }
    )

    ProductOrderDialog(
        showDialog = showQuantityDialog,
        onDismiss = {
            showQuantityDialog = false
        }
    ) {
        cartViewModel.addToCart(
            accessToken = userViewModel.getAccessToken() ?: "",
            productId = productId,
            quantity = 2
        )
    }


}

@Composable
fun AddToCartState(addToCartState: UiState<CartOperationResponse>) {

}





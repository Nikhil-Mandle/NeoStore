package com.nikhilproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhilproject.domain.model.CartListResponse
import com.nikhilproject.domain.model.CartOperationResponse
import com.nikhilproject.domain.usecase.AddToCartUseCase
import com.nikhilproject.domain.usecase.DeleteCartItemUseCase
import com.nikhilproject.domain.usecase.EditCartItemUseCase
import com.nikhilproject.domain.usecase.FetchCartItemUseCase
import com.nikhilproject.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val editCartUseCase: EditCartItemUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase,
    private val fetchCartItemUseCase: FetchCartItemUseCase
) : ViewModel() {

    private val _addToCartState = MutableStateFlow<UiState<CartOperationResponse>>(UiState.Idle)
    val addToCartState: StateFlow<UiState<CartOperationResponse>> = _addToCartState.asStateFlow()

    private val _editCartState = MutableStateFlow<UiState<CartOperationResponse>>(UiState.Idle)
    val editCartState: StateFlow<UiState<CartOperationResponse>> = _editCartState.asStateFlow()

    private val _deleteCartState = MutableStateFlow<UiState<CartOperationResponse>>(UiState.Idle)
    val deleteCartState: StateFlow<UiState<CartOperationResponse>> = _deleteCartState.asStateFlow()

    private val _cartListState = MutableStateFlow<UiState<CartListResponse>>(UiState.Idle)
    val cartListState: StateFlow<UiState<CartListResponse>> = _cartListState.asStateFlow()

    fun addToCart(accessToken: String, productId: Int, quantity: Int) = viewModelScope.launch {
        _addToCartState.value = UiState.Loading
        runCatching {
            addToCartUseCase(accessToken, productId, quantity)
        }.onSuccess { response ->
            _addToCartState.value = UiState.Success(response)
        }.onFailure { throwable ->
            _addToCartState.value = UiState.Error(throwable.message ?: "Unknown error")
        }
    }

    fun editCart(accessToken: String, productId: Int, quantity: Int) = viewModelScope.launch {
        _editCartState.value = UiState.Loading
        runCatching {
            editCartUseCase(accessToken, productId, quantity)
        }.onSuccess { response ->
            _editCartState.value = UiState.Success(response)
        }.onFailure { throwable ->
            _editCartState.value = UiState.Error(throwable.message ?: "Unknown error")
        }
    }

    fun deleteCartItem(accessToken: String, productId: Int) = viewModelScope.launch {
        _deleteCartState.value = UiState.Loading
        runCatching {
            deleteCartItemUseCase(accessToken, productId)
        }.onSuccess { response ->
            _deleteCartState.value = UiState.Success(response)
        }.onFailure { throwable ->
            _deleteCartState.value = UiState.Error(throwable.message ?: "Unknown error")
        }
    }

    fun fetchCartItems(accessToken: String) = viewModelScope.launch {
        _cartListState.value = UiState.Loading
        runCatching {
            fetchCartItemUseCase(accessToken)
        }.onSuccess { response ->
            _cartListState.value = UiState.Success(response)
        }.onFailure { throwable ->
            _cartListState.value = UiState.Error("No items in the cart")
        }
    }
}
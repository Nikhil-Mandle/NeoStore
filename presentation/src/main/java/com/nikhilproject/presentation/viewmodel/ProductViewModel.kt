package com.nikhilproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhilproject.domain.model.ProductDetailsData
import com.nikhilproject.domain.model.ProductItem
import com.nikhilproject.domain.usecase.FetchProductDetailsUseCase
import com.nikhilproject.domain.usecase.FetchProductListUseCase
import com.nikhilproject.domain.usecase.SetProductRatingUseCase
import com.nikhilproject.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val fetchProductListUseCase: FetchProductListUseCase,
    private val fetchProductDetailsUseCase: FetchProductDetailsUseCase,
    private val setProductRatingUseCase: SetProductRatingUseCase
) : ViewModel() {

    private val _productListState = MutableStateFlow<UiState<List<ProductItem>>>(UiState.Idle)
    val productListState: StateFlow<UiState<List<ProductItem>>> = _productListState.asStateFlow()

    private val _productDetailState = MutableStateFlow<UiState<ProductDetailsData>>(UiState.Idle)
    val productDetailState: StateFlow<UiState<ProductDetailsData>> =
        _productDetailState.asStateFlow()

    private val _setRatingState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val setRatingState: StateFlow<UiState<String>> = _setRatingState.asStateFlow()


    fun fetchProductList(productCategoryId: Int) = viewModelScope.launch {
        _productListState.value = UiState.Loading
        runCatching {
            fetchProductListUseCase(productCategoryId)
        }.onSuccess {
            _productListState.value = UiState.Success(it.data)
        }.onFailure {
            _productListState.value = UiState.Error(it.message ?: "Unable to load products")
        }
    }


    fun fetchProductDetails(productId: Int) = viewModelScope.launch {
        _productDetailState.value = UiState.Loading
        runCatching {
            fetchProductDetailsUseCase(productId).data
        }.onSuccess {
            _productDetailState.value = UiState.Success(it)
        }.onFailure {
            _productDetailState.value =
                UiState.Error(it.message ?: "Failed to load product details")
        }
    }

    fun setProductRating(productId: Int, rating: Int) = viewModelScope.launch {
        _setRatingState.value = UiState.Loading
        runCatching {
            setProductRatingUseCase(productId, rating)
        }.onSuccess {
            _setRatingState.value = UiState.Success(it.message)
        }.onFailure {
            _setRatingState.value = UiState.Error(it.message ?: "Failed to set rating")
        }
    }


}
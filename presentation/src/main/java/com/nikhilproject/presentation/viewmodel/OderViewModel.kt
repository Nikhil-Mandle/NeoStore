package com.nikhilproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhilproject.domain.model.FetchOrderDetailsData
import com.nikhilproject.domain.model.OrderDetails
import com.nikhilproject.domain.model.OrderResponse
import com.nikhilproject.domain.usecase.FetchOrderDetailUseCase
import com.nikhilproject.domain.usecase.GetAllOrderUseCase
import com.nikhilproject.domain.usecase.OrderProductUseCase
import com.nikhilproject.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderProductUseCase: OrderProductUseCase,
    private val getAllOrderUseCase: GetAllOrderUseCase,
    private val fetchOrderDetailUseCase: FetchOrderDetailUseCase
) : ViewModel() {

    private val _orderProductState = MutableStateFlow<UiState<OrderResponse>>(UiState.Idle)
    val orderProductState: StateFlow<UiState<OrderResponse>> = _orderProductState.asStateFlow()

    private val _allOrdersState = MutableStateFlow<UiState<List<OrderDetails>>>(UiState.Idle)
    val allOrdersState: StateFlow<UiState<List<OrderDetails>>> = _allOrdersState.asStateFlow()

    private val _orderDetailState = MutableStateFlow<UiState<FetchOrderDetailsData>>(UiState.Idle)
    val orderDetailState: StateFlow<UiState<FetchOrderDetailsData>> =
        _orderDetailState.asStateFlow()

    fun placeOrder(accessToken: String, address: String) = viewModelScope.launch {
        _orderProductState.value = UiState.Loading
        runCatching {
            orderProductUseCase(accessToken, address)
        }.onSuccess { response ->
            _orderProductState.value = UiState.Success(response)
        }.onFailure { throwable ->
            _orderProductState.value = UiState.Error(throwable.message ?: "Unknown error")
        }
    }

    fun getAllOrders(accessToken: String) = viewModelScope.launch {
        _allOrdersState.value = UiState.Loading
        runCatching {
            getAllOrderUseCase(accessToken).data
        }.onSuccess { orders ->
            _allOrdersState.value = UiState.Success(orders)
        }.onFailure { throwable ->
            _allOrdersState.value = UiState.Error(throwable.message ?: "Unknown error")
        }
    }

    fun fetchOrderDetail(accessToken: String, orderId: Int) = viewModelScope.launch {
        _orderDetailState.value = UiState.Loading
        runCatching {
            fetchOrderDetailUseCase(accessToken, orderId).data
        }.onSuccess { detail ->
            _orderDetailState.value = UiState.Success(detail)
        }.onFailure { throwable ->
            _orderDetailState.value = UiState.Error(throwable.message ?: "Unknown error")
        }
    }
}

package com.nikhilproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhilproject.domain.model.DashboardResponse
import com.nikhilproject.domain.model.ProductCategory
import com.nikhilproject.domain.usecase.FetchDashboardUseCase
import com.nikhilproject.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val dashboardUseCase: FetchDashboardUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ProductCategory>>>(UiState.Idle)
    val uiState: StateFlow<UiState<List<ProductCategory>>> = _uiState.asStateFlow()

    private val _dashboardUiState = MutableStateFlow<UiState<DashboardResponse>>(UiState.Idle)
    val dashboardUiState: StateFlow<UiState<DashboardResponse>> = _dashboardUiState.asStateFlow()

    fun fetchCategories(accessToken: String) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        runCatching { dashboardUseCase(accessToken).productData.product_categories }
            .onSuccess { _uiState.value = UiState.Success(it) }
            .onFailure { _uiState.value = UiState.Error(it.message ?: "Unknown error") }
    }

    fun fetchDashboard(accessToken: String) = viewModelScope.launch {
        _dashboardUiState.value = UiState.Loading
        runCatching { dashboardUseCase(accessToken) }
            .onSuccess { _dashboardUiState.value = UiState.Success(it) }
            .onFailure { _dashboardUiState.value = UiState.Error(it.message ?: "Unknown error") }
    }
}
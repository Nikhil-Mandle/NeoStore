package com.nikhilproject.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhilproject.domain.model.addressmodel.Address
import com.nikhilproject.domain.usecase.addressusecase.AddAddressUseCase
import com.nikhilproject.domain.usecase.addressusecase.DeleteAddressUseCase
import com.nikhilproject.domain.usecase.addressusecase.GetAllAddressesUseCase
import com.nikhilproject.domain.usecase.addressusecase.UpdateAddressUseCase
import com.nikhilproject.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addAddressUseCase: AddAddressUseCase,
    private val updateAddressUseCase: UpdateAddressUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase,
    private val getAllAddressesUseCase: GetAllAddressesUseCase
) : ViewModel() {

    private val _addAddressState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val addAddressState = _addAddressState.asStateFlow()

    private val _updateAddressState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val updateAddressState = _updateAddressState.asStateFlow()

    // Delete Address State
    private val _deleteAddressState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val deleteAddressState = _deleteAddressState.asStateFlow()

    fun addAddress(address: Address) = viewModelScope.launch {
        _addAddressState.value = UiState.Loading
        runCatching {
            addAddressUseCase(address)
        }.onSuccess {
            _addAddressState.value = UiState.Success("Address added successfully")
        }.onFailure { e ->
            _addAddressState.value = UiState.Error(e.message ?: "Unknown error")
        }
    }

    fun updateAddress(address: Address) = viewModelScope.launch {
        _updateAddressState.value = UiState.Loading
        runCatching {
            updateAddressUseCase(address)
        }.onSuccess {
            _updateAddressState.value = UiState.Success(Unit)
        }.onFailure { e ->
            _updateAddressState.value = UiState.Error(e.message ?: "Unknown error")
        }
    }

    fun deleteAddress(address: Address) = viewModelScope.launch {
        _deleteAddressState.value = UiState.Loading
        runCatching {
            deleteAddressUseCase(address)
        }.onSuccess {
            _deleteAddressState.value = UiState.Success("Address deleted successfully")
        }.onFailure { e ->
            _deleteAddressState.value = UiState.Error(e.message ?: "Unknown error")
        }
    }

    val allAddresses = getAllAddressesUseCase()
        .catch { e ->
            Log.e("AddressViewModel", "Failed to load addresses", e)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

}
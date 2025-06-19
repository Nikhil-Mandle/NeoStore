package com.nikhilproject.presentation

import com.nikhilproject.domain.model.User

/*data class UiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)*/

sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

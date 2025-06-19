package com.nikhilproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhilproject.domain.model.LogInRequest
import com.nikhilproject.domain.model.RegisterRequest
import com.nikhilproject.domain.model.User
import com.nikhilproject.domain.usecase.LoginUserUseCase
import com.nikhilproject.domain.usecase.RegisterUserUseCase
import com.nikhilproject.presentation.TokenManager
import com.nikhilproject.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registerUseCase: RegisterUserUseCase,
    private val loginUseCase: LoginUserUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<User>>(UiState.Idle)
    val uiState: StateFlow<UiState<User>> = _uiState.asStateFlow()

    fun register(req: RegisterRequest) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        runCatching { registerUseCase(req) }
            .onSuccess { user ->
                tokenManager.addAccessToken(user.accessToken)
                _uiState.value = UiState.Success(user)
            }
            .onFailure { ex ->
                _uiState.value = UiState.Error(ex.message ?: "Something went wrong")
            }
    }


    fun login(req: LogInRequest) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        runCatching { loginUseCase(req) }
            .onSuccess { user ->
                tokenManager.addAccessToken(user.accessToken)
                _uiState.value = UiState.Success(user)
            }
            .onFailure { ex ->
                _uiState.value = UiState.Error(ex.message ?: "Something went wrong")
            }
    }

    fun getAccessToken() = tokenManager.getAccessToken()

}

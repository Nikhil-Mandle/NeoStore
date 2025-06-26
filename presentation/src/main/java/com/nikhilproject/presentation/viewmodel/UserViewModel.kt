package com.nikhilproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhilproject.domain.model.LogInRequest
import com.nikhilproject.domain.model.RegisterRequest
import com.nikhilproject.domain.model.User
import com.nikhilproject.domain.usecase.ChangePasswordUseCase
import com.nikhilproject.domain.usecase.LoginUserUseCase
import com.nikhilproject.domain.usecase.RegisterUserUseCase
import com.nikhilproject.domain.usecase.UpdateProfileUseCase
import com.nikhilproject.presentation.SharedPreferenceManager
import com.nikhilproject.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val registerUseCase: RegisterUserUseCase,
    private val loginUseCase: LoginUserUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<User>>(UiState.Idle)
    val uiState: StateFlow<UiState<User>> = _uiState.asStateFlow()

    private val _changePasswordUiState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val changePasswordUiState: StateFlow<UiState<String>> = _changePasswordUiState.asStateFlow()

    private val _updateProfileUiState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val updateProfileUiState: StateFlow<UiState<String>> = _updateProfileUiState.asStateFlow()

    fun register(req: RegisterRequest) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        runCatching { registerUseCase(req) }
            .onSuccess { user ->
                sharedPreferenceManager.addAccessToken(user.accessToken)
                sharedPreferenceManager.saveUserName(user.firstName + " " + user.lastName)
                sharedPreferenceManager.saveUserEmail(user.email)
                user.profilePic?.let { sharedPreferenceManager.saveProfilePic(it) }
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
                sharedPreferenceManager.addAccessToken(user.accessToken)
                sharedPreferenceManager.saveUserName(user.firstName + " " + user.lastName)
                sharedPreferenceManager.saveUserEmail(user.email)
                user.profilePic?.let { sharedPreferenceManager.saveProfilePic(it) }
                _uiState.value = UiState.Success(user)
            }
            .onFailure { ex ->
                _uiState.value = UiState.Error(ex.message ?: "Something went wrong")
            }
    }

    fun changePassword(
        token: String,
        oldPassword: String,
        password: String,
        confirmPassword: String
    ) = viewModelScope.launch {
        _changePasswordUiState.value = UiState.Loading
        runCatching {
            changePasswordUseCase(
                token = token,
                old_password = oldPassword,
                password = password,
                confirm_password = confirmPassword
            )
        }.onSuccess {
            _changePasswordUiState.value = UiState.Success(it.message)
        }.onFailure {
            _changePasswordUiState.value = UiState.Error(it.message ?: "Something went wrong")
        }
    }


    fun updateProfile(
        token: String,
        firstName: String,
        lastName: String,
        email: String,
        dob: String,
        phoneNo: String,
        profilePic: String
    ) = viewModelScope.launch {
        _updateProfileUiState.value = UiState.Loading
        runCatching {
            updateProfileUseCase(
                token = token,
                firstName = firstName,
                lastName = lastName,
                email = email,
                dob = dob,
                phoneNo = phoneNo,
                profilePic = profilePic
            )
        }.onSuccess {
            _updateProfileUiState.value = UiState.Success(it.message)
        }.onFailure {
            _updateProfileUiState.value = UiState.Error(it.message ?: "Something went wrong")
        }
    }


    fun getAccessToken() = sharedPreferenceManager.getAccessToken()

    fun getUserName() = sharedPreferenceManager.getUserName()

    fun getUserEmail() = sharedPreferenceManager.getUserEmail()

    fun getProfilePic() = sharedPreferenceManager.getProfilePic()

}

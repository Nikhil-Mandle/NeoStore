package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.ResetPasswordResponse
import com.nikhilproject.domain.repository.UserRepository

class ChangePasswordUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        token: String,
        old_password: String,
        password: String,
        confirm_password: String
    ): ResetPasswordResponse {
        return repository.changePassword(
            token = token,
            old_password = old_password,
            password = password,
            confirm_password = confirm_password
        )
    }
}
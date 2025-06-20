package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.ForgetPasswordResponse
import com.nikhilproject.domain.repository.UserRepository

class ForgetPasswordUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String): ForgetPasswordResponse {
        return repository.forgetPassword(email)
    }
}
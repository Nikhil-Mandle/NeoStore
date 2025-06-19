package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.LogInRequest
import com.nikhilproject.domain.model.User
import com.nikhilproject.domain.repository.UserRepository

class LoginUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(request: LogInRequest): User {
        return userRepository.login(request)
    }
}
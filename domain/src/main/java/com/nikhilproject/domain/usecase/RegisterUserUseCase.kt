package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.RegisterRequest
import com.nikhilproject.domain.model.User
import com.nikhilproject.domain.repository.UserRepository

class RegisterUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(request: RegisterRequest): User {
        return userRepository.register(request)
    }
}

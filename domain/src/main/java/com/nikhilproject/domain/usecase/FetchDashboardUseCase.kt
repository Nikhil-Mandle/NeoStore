package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.DashboardResponse
import com.nikhilproject.domain.repository.UserRepository

class FetchDashboardUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(accessToken: String): DashboardResponse {
        return userRepository.fetchUserAccountDetails(accessToken = accessToken)
    }
}

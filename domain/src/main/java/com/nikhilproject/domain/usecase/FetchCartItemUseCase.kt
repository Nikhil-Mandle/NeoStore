package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.CartListResponse
import com.nikhilproject.domain.repository.CartRepository

class FetchCartItemUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(accessToken: String): CartListResponse {
        return cartRepository.getCartItems(accessToken)
    }
}
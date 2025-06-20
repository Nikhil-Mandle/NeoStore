package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.CartOperationResponse
import com.nikhilproject.domain.repository.CartRepository

class DeleteCartItemUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(accessToken: String, productId: Int): CartOperationResponse {
        return cartRepository.deleteCartItem(accessToken, productId)
    }
}
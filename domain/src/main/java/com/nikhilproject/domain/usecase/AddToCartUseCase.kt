package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.CartOperationResponse
import com.nikhilproject.domain.repository.CartRepository

class AddToCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(
        accessToken: String,
        productId: Int,
        quantity: Int
    ): CartOperationResponse {
        return cartRepository.addToCart(accessToken, productId = productId, quantity = quantity)
    }
}
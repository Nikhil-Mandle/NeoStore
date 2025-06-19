package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.SetProductRatingResponse
import com.nikhilproject.domain.repository.ProductRepository

class SetProductRatingUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: Int, rating: Int): SetProductRatingResponse {
        return productRepository.setProductRating(productId, rating)
    }

}
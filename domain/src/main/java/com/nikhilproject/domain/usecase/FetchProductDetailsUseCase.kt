package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.ProductDetailResponse
import com.nikhilproject.domain.repository.ProductRepository

class FetchProductDetailsUseCase(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(productId: Int): ProductDetailResponse {
        return productRepository.getProductDetails(productId)
    }
}
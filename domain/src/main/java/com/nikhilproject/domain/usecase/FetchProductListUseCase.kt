package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.model.ProductsListResponse
import com.nikhilproject.domain.repository.ProductRepository

class FetchProductListUseCase(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(productCategoryId: Int): ProductsListResponse {
        return productRepository.getProductList(productCategoryId = productCategoryId)
    }
}
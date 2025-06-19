package com.nikhilproject.data.repositoryimpl

import com.nikhilproject.data.api.ProductApiService
import com.nikhilproject.data.mapper.toDomain
import com.nikhilproject.domain.model.ProductDetailResponse
import com.nikhilproject.domain.model.ProductsListResponse
import com.nikhilproject.domain.model.SetProductRatingResponse
import com.nikhilproject.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApiService: ProductApiService
): ProductRepository {

    override suspend fun getProductList(productCategoryId: Int): ProductsListResponse {
        val response = productApiService.getProductList(productCategoryId)
        return response.toDomain()
    }

    override suspend fun getProductDetails(productId: Int): ProductDetailResponse {
        val response = productApiService.getProductDetails(productId)
        return response.toDomain()
    }

    override suspend fun setProductRating(productId: Int, rating: Int): SetProductRatingResponse {
        val response = productApiService.setProductRating(productId, rating)
        return response.toDomain()
    }

}
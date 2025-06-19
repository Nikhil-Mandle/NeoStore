package com.nikhilproject.domain.repository

import com.nikhilproject.domain.model.ProductDetailResponse
import com.nikhilproject.domain.model.ProductsListResponse
import com.nikhilproject.domain.model.SetProductRatingResponse

interface ProductRepository {

    suspend fun getProductList(productCategoryId: Int): ProductsListResponse

    suspend fun getProductDetails(productId: Int): ProductDetailResponse

    suspend fun setProductRating(productId: Int, rating: Int): SetProductRatingResponse

}
package com.nikhilproject.data.api

import com.nikhilproject.data.model.dto.ProductDetailsResponseDto
import com.nikhilproject.data.model.dto.ProductsListResponseDto
import com.nikhilproject.data.model.dto.SetProductRatingDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApiService {

    @GET("api/products/getList")
    suspend fun getProductList(
        @Query("product_category_id") productCategoryId: Int
    ): ProductsListResponseDto

    @GET("api/products/getDetail")
    suspend fun getProductDetails(
        @Query("product_id") productId: Int
    ): ProductDetailsResponseDto

    @FormUrlEncoded
    @POST("api/products/setRating")
    suspend fun setProductRating(
        @Field("product_id") productId: Int,
        @Field("rating") rating: Int
    ): SetProductRatingDto
}
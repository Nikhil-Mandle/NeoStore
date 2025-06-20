package com.nikhilproject.data.api

import com.nikhilproject.data.model.dto.CartListResponseDto
import com.nikhilproject.data.model.dto.CartOperationResponseDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CartApiService {

    @GET("api/cart")
    suspend fun getCartItems(
        @Header("access_token") accessToken: String
    ): CartListResponseDto

    @FormUrlEncoded
    @POST("api/editCart")
    suspend fun editCartItems(
        @Header("access_token") accessToken: String,
        @Field("product_id") productId: Int,
        @Field("quantity") quantity: Int
    ): CartOperationResponseDto

    @FormUrlEncoded
    @POST("api/deleteCart")
    suspend fun deleteCartItem(
        @Header("access_token") accessToken: String,
        @Field("product_id") productId: Int
    ): CartOperationResponseDto

    @FormUrlEncoded
    @POST("api/addToCart")
    suspend fun addToCart(
        @Header("access_token") accessToken: String,
        @Field("product_id") productId: Int,
        @Field("quantity") quantity: Int
    ): CartOperationResponseDto
}
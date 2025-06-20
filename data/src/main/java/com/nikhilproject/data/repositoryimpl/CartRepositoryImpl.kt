package com.nikhilproject.data.repositoryimpl

import com.nikhilproject.data.api.CartApiService
import com.nikhilproject.data.mapper.toDomain
import com.nikhilproject.domain.model.CartListResponse
import com.nikhilproject.domain.model.CartOperationResponse
import com.nikhilproject.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartApiService: CartApiService
) : CartRepository {

    override suspend fun getCartItems(accessToken: String): CartListResponse {
        val response = cartApiService.getCartItems(accessToken)
        return response.toDomain()
    }

    override suspend fun editCartItems(
        accessToken: String,
        productId: Int,
        quantity: Int
    ): CartOperationResponse {
        val response = cartApiService.editCartItems(
            accessToken = accessToken,
            productId = productId,
            quantity = quantity
        )
        return response.toDomain()
    }

    override suspend fun deleteCartItem(
        accessToken: String,
        productId: Int
    ): CartOperationResponse {

        val response =
            cartApiService.deleteCartItem(accessToken = accessToken, productId = productId)
        return response.toDomain()
    }

    override suspend fun addToCart(
        accessToken: String,
        productId: Int,
        quantity: Int
    ): CartOperationResponse {
        val response = cartApiService.addToCart(
            accessToken = accessToken,
            productId = productId,
            quantity = quantity
        )
        return response.toDomain()
    }
}
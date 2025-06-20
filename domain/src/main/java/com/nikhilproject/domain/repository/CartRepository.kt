package com.nikhilproject.domain.repository

import com.nikhilproject.domain.model.CartListResponse
import com.nikhilproject.domain.model.CartOperationResponse

interface CartRepository {
    suspend fun getCartItems(accessToken: String): CartListResponse
    suspend fun editCartItems(accessToken: String, productId: Int, quantity: Int): CartOperationResponse
    suspend fun deleteCartItem(accessToken: String, productId: Int): CartOperationResponse
    suspend fun addToCart(accessToken: String, productId: Int, quantity: Int): CartOperationResponse

}
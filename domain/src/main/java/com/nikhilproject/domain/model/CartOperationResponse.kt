package com.nikhilproject.domain.model

data class CartOperationResponse(
    val data: Boolean,
    val message: String,
    val status: Int,
    val totalCarts: Int,
    val userMsg: String
)
package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName

data class CartOperationResponseDto(
    @SerializedName("data") val data: Boolean,
    val message: String,
    val status: Int,
    @SerializedName("total_carts") val totalCarts: Int,
    @SerializedName("user_msg") val userMsg: String
)
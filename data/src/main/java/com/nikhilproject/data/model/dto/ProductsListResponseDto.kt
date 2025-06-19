package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName


data class ProductsListResponseDto(
    @SerializedName("data") val productItemDto: List<ProductItemDto>,
    val status: Int
)
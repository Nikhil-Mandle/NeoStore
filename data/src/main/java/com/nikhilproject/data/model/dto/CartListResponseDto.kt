package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName

data class CartListResponseDto(
    val count: Int,
    @SerializedName("data") val productItem: List<ProductItemDataDto>,
    val status: Int,
    val total: Int
)

data class ProductItemDataDto(
    val id: Int,
    val product: ProductDto,
    @SerializedName("product_id") val productId: Int,
    val quantity: Int
)

data class ProductDto(
    val cost: Int,
    val id: Int,
    val name: String,
    @SerializedName("product_category") val productCategory: String,
    @SerializedName("product_images") val productImages: String,
    @SerializedName("sub_total") val subTotal: Int
)
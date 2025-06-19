package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName

class ProductDetailsResponseDto(
    @SerializedName("data") val productDetailsDataDto: ProductDetailsDataDto,
    val status: Int
)

data class ProductDetailsDataDto(
    val cost: Int,
    val created: String,
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val producer: String,
    val product_category_id: Int,
    val product_images: List<ProductImageDto>,
    val rating: Int,
    val view_count: Int
)

data class ProductImageDto(
    val created: String,
    val id: Int,
    val image: String,
    val modified: String,
    val product_id: Int
)
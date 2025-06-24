package com.nikhilproject.domain.model

data class SetProductRatingResponse(
    val data: ProductRatingData,
    val message: String,
    val status: Int,
    val user_msg: String
)

data class ProductRatingData(
    val id: Int,
    val productCategoryId: Int,
    val name: String,
    val producer: String,
    val description: String,
    val cost: Int,
    val rating: Int,
    val viewCount: Int,
    val created: String,
    val modified: String,
)
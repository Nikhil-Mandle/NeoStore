package com.nikhilproject.domain.model

data class SetProductRatingResponse(
    val data: ProductDetailsData,
    val message: String,
    val status: Int,
    val user_msg: String
)
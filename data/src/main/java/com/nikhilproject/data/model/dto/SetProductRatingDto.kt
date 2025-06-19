package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName

data class SetProductRatingDto(
    @SerializedName("data") val data: ProductDetailsDataDto,
    val message: String,
    val status: Int,
    val user_msg: String
)
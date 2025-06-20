package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName

data class GetAllOrderResponseDto(
    @SerializedName("data")
    val data: List<OrderDetailsDto>,
    val status: Int
)

data class OrderDetailsDto(
    val cost: Double,
    val created: String,
    val id: Int
)
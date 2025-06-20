package com.nikhilproject.domain.model

data class GetAllOrderResponse(
    val data: List<OrderDetails>,
    val status: Int
)

data class OrderDetails(
    val cost: Double,
    val created: String,
    val id: Int
)

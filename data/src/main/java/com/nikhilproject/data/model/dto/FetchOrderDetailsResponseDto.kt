package com.nikhilproject.data.model.dto

data class FetchOrderDetailsResponseDto(
    val data: FetchOrderDetailsDataDto,
    val status: Int
)

data class FetchOrderDetailsDataDto(
    val cost: Double,
    val address: String,
    val id: Int,
    val order_details: List<OneOrderDetailDto>
)

data class OneOrderDetailDto(
    val id: Int,
    val order_id: Int,
    val prod_cat_name: String,
    val prod_image: String,
    val prod_name: String,
    val product_id: Int,
    val quantity: Int,
    val total: Int
)

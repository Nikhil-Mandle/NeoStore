package com.nikhilproject.domain.model

data class FetchOrderDetailsResponse(
    val data: FetchOrderDetailsData,
    val status: Int
)

data class FetchOrderDetailsData(
    val cost: Double,
    val address: String,
    val id: Int,
    val order_details: List<OneOrderDetail>
)

data class OneOrderDetail(
    val id: Int,
    val order_id: Int,
    val prod_cat_name: String,
    val prod_image: String,
    val prod_name: String,
    val product_id: Int,
    val quantity: Int,
    val total: Int
)
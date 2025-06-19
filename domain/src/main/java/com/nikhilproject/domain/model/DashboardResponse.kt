package com.nikhilproject.domain.model

import java.io.Serializable

data class DashboardResponse(
    val productData: ProductData,
    val status: Int,
)

data class ProductData(
    val product_categories: List<ProductCategory>,
    val total_carts: Int,
    val total_orders: Int,
    val user_data: User
)

data class ProductCategory(
    val created: String,
    val icon_image: String,
    val id: Int,
    val modified: String,
    val name: String
) : Serializable
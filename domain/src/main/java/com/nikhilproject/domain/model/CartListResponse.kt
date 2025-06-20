package com.nikhilproject.domain.model

data class CartListResponse(
    val count: Int,
    val data: List<ProductItemData>,
    val status: Int,
    val total: Int
)

data class ProductItemData(
    val id: Int,
    val product: Product,
    val product_id: Int,
    val quantity: Int
)

data class Product(
    val cost: Int,
    val id: Int,
    val name: String,
    val product_category: String,
    val product_images: String,
    val sub_total: Int
)
package com.nikhilproject.domain.model

data class ProductsListResponse(
    val data: List<ProductItem>,
    val status: Int
)
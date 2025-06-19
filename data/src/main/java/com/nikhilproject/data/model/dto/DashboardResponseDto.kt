package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName
import com.nikhilproject.domain.model.DashboardResponse
import com.nikhilproject.domain.model.User
import java.io.Serializable

data class DashboardResponseDto(
    @SerializedName("data") val productData: ProductData,
    @SerializedName("status") val status: Int,
) {

}

data class ProductData(
    val product_categories: List<ProductCategory>,
    val total_carts: Int,
    val total_orders: Int,
    val user_data: UserDto
)

data class ProductCategory(
    val created: String,
    val icon_image: String,
    val id: Int,
    val modified: String,
    val name: String
) : Serializable
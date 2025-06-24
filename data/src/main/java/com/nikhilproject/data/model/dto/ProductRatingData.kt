package com.nikhilproject.data.model.dto

import com.google.gson.annotations.SerializedName
import com.nikhilproject.domain.model.ProductRatingData

data class ProductRatingDataDto(
    val id: Int,
    @SerializedName("product_category_id")
    val productCategoryId: Int,
    val name: String,
    val producer: String,
    val description: String,
    val cost: Int,
    val rating: Int,
    @SerializedName("view_count")
    val viewCount: Int,
    val created: String,
    val modified: String,
) {
    fun toDomain(): ProductRatingData {
        return ProductRatingData(
            id = id,
            productCategoryId = productCategoryId,
            name = name,
            producer = producer,
            description = description,
            cost = cost,
            rating = rating,
            viewCount = viewCount,
            created = created,
            modified = modified,
        )
    }
}